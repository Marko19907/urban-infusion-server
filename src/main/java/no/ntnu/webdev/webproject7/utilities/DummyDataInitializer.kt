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
            logger.info("The database is not empty, aborting...");
            return;
        }

        arrayOf(
            Comment("0", "0", "0", "Very nice", null),
            Comment("1", "1", "1", "I love this product", null),
            Comment("2", "2", "2", "This product sucks!", null)
        ).forEach { commentRepository.save(it) }

        arrayOf(
            Product("0", 99.99, 0.00, null, "Black tea", "Description text", "10oz"),
            Product("1", 49.99, 0.50, null, "Green tea", "Description text", "20oz"),
            Product("2", 19.99, 0.15, null, "White tea", "Description text", "5oz")
        ).forEach { productRepository.save(it) }

        arrayOf(
            UserEntity("0", false, "user@gmail.com", "123"),
            UserEntity("1", true, "admin@teashop.com", "321"),
            UserEntity("2", false, "user@example.no", "987")
        ).forEach { userRepository.save(it) }

        logger.info("Test data initialized");
    }
}
