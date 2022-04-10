package no.ntnu.webdev.webproject7.utilities

import no.ntnu.webdev.webproject7.models.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import no.ntnu.webdev.webproject7.repositories.UserRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DummyDataInitializer(
    private val commentRepository: CommentRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
) : ApplicationListener<ApplicationReadyEvent> {

    private val logger: Logger = LoggerFactory.getLogger("DummyDataInitializer");

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        logger.info("Initializing test data...");

        // Check if all repositories are empty
        if (arrayOf(
                commentRepository,
                productRepository,
                userRepository,
                orderRepository
            ).any { it.count() > 0 }
        ) {
            logger.info("The database is not empty, did not initialize test data...");
            return;
        }

        val user1 = UserEntity("user@gmail.com", "user", "user", "Ålesund", "6008", "Vågavegen 29", "98765432");
        val user2 = UserEntity("admin@teashop.com", "admin", "admin", "Oslo", "0001", "Majorstuen 5", "98876543");
        user2.setRole(Role.ADMIN);
        val user3 = UserEntity("user@example.no", "other_user", "987", "Bergen", "5003", "Juvik 12", "43219876");

        val comment1 = Comment(user1, "Very nice", null);
        val comment2 = Comment(user2, "I love this product", LocalDate.now());
        val comment3 = Comment(user3, "This product sucks!", LocalDate.now());

        val product1 =
            Product(mutableListOf(comment1), 99.99, 0.00, null, "Black tea", "Description text", "10oz", Category.TEA);
        val product2 =
            Product(mutableListOf(comment2), 49.99, 0.50, null, "Green tea", "Description text", "20oz", Category.TEA);
        val product3 =
            Product(mutableListOf(comment3), 19.99, 0.15, null, "White tea", "Description text", "5oz", Category.TEA);

        val orderEntity1 = OrderEntity(mutableListOf(product1), OrderStatus.IDLE, 20f);
        val orderEntity2 = OrderEntity(mutableListOf(product2, product3), OrderStatus.PROCESSING, 100f);

        val users = arrayOf(user1, user2, user3)
        val comments = arrayOf(comment1, comment2, comment3)
        val products = arrayOf(product1, product2, product3)
        val orders = arrayOf(orderEntity1, orderEntity2)

        users.forEach { userRepository.save(it) }
        products.forEach { productRepository.save(it) }
        comments.forEach { commentRepository.save(it) }
        orders.forEach { orderRepository.save(it) }

        logger.info("Test data initialized");
    }
}
