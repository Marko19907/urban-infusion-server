/*
package no.ntnu.webdev.webproject7

import no.ntnu.webdev.webproject7.comment.Comment
import no.ntnu.webdev.webproject7.comment.CommentRepository
import no.ntnu.webdev.webproject7.product.Product
import no.ntnu.webdev.webproject7.product.ProductRepository
import no.ntnu.webdev.webproject7.user.UserEntity
import no.ntnu.webdev.webproject7.user.UserRepository
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
@ActiveProfiles("test")
internal class Webproject7ApplicationTests(
    @Autowired
    private val commentRepository: CommentRepository,

    @Autowired
    private val productRepository: ProductRepository,

    @Autowired
    private val userRepository: UserRepository
) {
    @Value("\${spring.datasource.url}")
    private lateinit var dataSource: String;

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass);

    @Test
    fun contextLoads() {
        logger.info(dataSource);
    }

    @Test
    fun dbCrudTests() {
        assertEquals(commentRepository.count(), 3);
        assertEquals(productRepository.count(), 3);
        assertEquals(userRepository.count(), 3);


        //commentRepository.deleteAll();
        //productRepository.deleteAll();
        //userRepository.deleteAll();

        arrayOf(commentRepository, productRepository, userRepository)
            .forEach { repository -> assertTrue { repository.count() == 0L } }

        val user = UserEntity(false, "user@example.com", "4321");
        val comment = Comment(user, "Ice wallow come", LocalDate.now());
        val product = Product(mutableListOf(comment), 0.0, null, "Black tea", "Description text 123", "15oz");

        productRepository.save(product);
        userRepository.save(user);
        commentRepository.save(comment);

        assertEquals(productRepository.count(), 1);
        assertEquals(userRepository.count(), 1);
        assertEquals(commentRepository.count(), 1);

        assertTrue { productRepository.findAll().any { it.id == product.id } }
        assertTrue { userRepository.findAll().any { it.id == user.id } }
        assertTrue { commentRepository.findAll().any { it.id == comment.id } }

        // assertEquals(productRepository.findById("100").get().id, product.id)
        // assertEquals(userRepository.findById("100").get().id, user.id)
        // assertEquals(commentRepository.findById("100").get().id, comment.id)

        commentRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();

        assertEquals(productRepository.count(), 0);
        assertEquals(userRepository.count(), 0);
        assertEquals(commentRepository.count(), 0);
    }
}*/
