package no.ntnu.webdev.webproject7

import no.ntnu.webdev.webproject7.comment.CommentRepository
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals

@SpringBootTest
@ActiveProfiles("test")
internal class Webproject7ApplicationTests(
    @Autowired
    private val commentRepository: CommentRepository
) {
    @Value("\${spring.datasource.url}")
    private lateinit var dataSource: String;

    @Test
    fun contextLoads() {
        val logger: Logger = LoggerFactory.getLogger(this.javaClass);
        logger.info(dataSource);
    }

    @Test
    fun dbCrudTests() {
        assertEquals(commentRepository.count(), 3);

    }
}