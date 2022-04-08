package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, CommentId>