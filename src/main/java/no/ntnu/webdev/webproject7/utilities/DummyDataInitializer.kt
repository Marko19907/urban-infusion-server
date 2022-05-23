package no.ntnu.webdev.webproject7.utilities

import com.thedeanda.lorem.LoremIpsum
import no.ntnu.webdev.webproject7.models.*
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import no.ntnu.webdev.webproject7.repositories.OrdersProductsRepository
import no.ntnu.webdev.webproject7.repositories.ProductImageRepository
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import no.ntnu.webdev.webproject7.repositories.UserImageRepository
import no.ntnu.webdev.webproject7.repositories.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DummyDataInitializer(
    private val commentRepository: CommentRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository,
    private val productImageRepository: ProductImageRepository,
    private val ordersProductsRepository: OrdersProductsRepository,
    private val userImageRepository: UserImageRepository
) : ApplicationListener<ApplicationReadyEvent> {

    private val logger: Logger = LoggerFactory.getLogger("DummyDataInitializer");

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        this.logger.info("Initializing test data...");

        // Check if all repositories are empty
        if (arrayOf(
                this.commentRepository,
                this.productRepository,
                this.userRepository,
                this.orderRepository,
                this.productImageRepository,
                this.ordersProductsRepository,
                this.userImageRepository
            ).any { it.count() > 0 }
        ) {
            this.logger.info("The database is not empty, did not initialize test data...");
            return;
        }

        val user1 = User("user@gmail.com", "user", hashPassword("user"), "Ålesund", "6008", "Vågavegen 29", "98765432");
        val user2 = User("admin@teashop.com", "admin", hashPassword("admin"), "Oslo", "0001", "Majorstuen 5", "98876543");
        user2.role = Role.ADMIN;
        val user3 = User("user@example.no", "other_user", hashPassword("987"), "Bergen", "5003", "Juvik 12", "43219876");

        val comment1 = Comment(user1, "Very nice", null);
        val comment2 = Comment(user2, "I love this product", LocalDate.now());
        val comment3 = Comment(user3, "This product sucks!", LocalDate.now());
        val comment4 = Comment(user1, this.getLoremIpsum(), LocalDate.of(2020, 12, 12));

        val product1 =
            Product(mutableListOf(comment1), 99.99, 0.00, 1, "Black tea", "Description text", "10oz", Category.TEA);
        val product2 =
            Product(mutableListOf(comment2), 49.99, 0.50, 2, "Green tea", "Description text", "20oz", Category.TEA);
        val product3 =
            Product(mutableListOf(comment3, comment4), 19.99, 0.15, null, "White tea", this.getLoremIpsum(), "5oz", Category.TEA);
        val product4 =
            Product(mutableListOf(), 4.99, 0.00, 4, "Tea cup", "The elegant Urban Infusion™ tea cup. Do you need a boost in the morning? That's exactly what our cup is for! Now is the time to add this classic cup to your tea collection!", "6oz", Category.ACCESSORIES);
        val product5 =
            Product(mutableListOf(), 29.99, 0.00, 5, "Golden tea", "Want to feel golden again? The distinctive blend and it's creamy, velvet taste help create an infusion that is both calming and refreshing. Don't miss this \"golden\" opportunity!", "10oz", Category.TEA);

        val ordersProducts1 = OrdersProducts(product1, 2);
        val ordersProducts2 = OrdersProducts(product4, 1);
        val ordersProducts3 = OrdersProducts(product3, 5);

        val order1 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.IDLE, user1);
        val order2 = Order(mutableListOf(ordersProducts3), OrderStatus.PROCESSING, user3);

        val productImage1 = ProductImage(1 ,"1-BlackTea", "png");
        val productImage2 = ProductImage(2 ,"2-GreenTea", "png");
        val productImage4 = ProductImage(4 ,"4-TeaCup", "png");
        val productImage5 = ProductImage(5 ,"5-GoldenTea", "png");

        val userImage1 = UserImage(1, "User1", "jpeg");

        val users = arrayOf(user1, user2, user3);
        val comments = arrayOf(comment1, comment2, comment3, comment4);
        val ordersProducts =  arrayOf(ordersProducts1, ordersProducts2, ordersProducts3);
        val products = arrayOf(product1, product2, product3, product4, product5);
        val orders = arrayOf(order1, order2);
        val productImages = arrayOf(productImage1, productImage2, productImage4, productImage5);
        val userImages = arrayOf(userImage1);

        users.forEach { this.userRepository.save(it) }
        products.forEach { this.productRepository.save(it) }
        comments.forEach { this.commentRepository.save(it) }
        ordersProducts.forEach { this.ordersProductsRepository.save(it) }
        orders.forEach { this.orderRepository.save(it) }
        productImages.forEach { this.productImageRepository.save(it) }
        userImages.forEach { this.userImageRepository.save(it) }

        this.logger.info("Test data initialized");
    }

    private fun getLoremIpsum(): String {
        return LoremIpsum.getInstance().getWords(20, 50);
    }
}
