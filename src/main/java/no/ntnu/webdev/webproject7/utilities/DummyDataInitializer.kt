package no.ntnu.webdev.webproject7.utilities

import no.ntnu.webdev.webproject7.comment.Comment
import no.ntnu.webdev.webproject7.comment.CommentRepository
import no.ntnu.webdev.webproject7.product.Product
import no.ntnu.webdev.webproject7.product.ProductRepository
import no.ntnu.webdev.webproject7.user.UserEntity
import no.ntnu.webdev.webproject7.user.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class DummyDataInitializer(
    private val commentRepository: CommentRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository
) : ApplicationListener<ApplicationReadyEvent> {

    private val logger: Logger = LoggerFactory.getLogger("DummyDataInitializer");

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        logger.info("Initializing test data...");

        // Check if all repositories are empty
        if (arrayOf(commentRepository.count(), productRepository.count(), userRepository.count()).any { e -> e > 0 }) {
            logger.info("The database is not empty, did not initialize test data...");
            return;
        }

        val product1 = Product("1", 99.99, 0.00, null, "Black tea", "Description text", "10oz");
        val product2 = Product("2", 49.99, 0.50, null, "Green tea", "Description text", "20oz");
        val product3 = Product("3", 19.99, 0.15, null, "White tea", "Description text", "5oz");

        val user1 = UserEntity("1", false, "user@gmail.com", "123");
        val user2 = UserEntity("2", true, "admin@teashop.com", "321");
        val user3 = UserEntity("3", false, "user@example.no", "987");

        val comment1 = Comment("1", product1, user1, "Very nice", null);
        val comment2 = Comment("2", product2, user2, "I love this product", null);
        val comment3 = Comment("3", product3, user3, "This product sucks!", null);


        arrayOf(
            comment1,
            comment2,
            comment3
        ).forEach { commentRepository.save(it) }

        arrayOf(
                product1,
                product2,
                product3
        ).forEach { productRepository.save(it) }

        arrayOf(
                user1,
                user2,
                user3
        ).forEach { userRepository.save(it) }

        logger.info("Test data initialized");
    }
}
