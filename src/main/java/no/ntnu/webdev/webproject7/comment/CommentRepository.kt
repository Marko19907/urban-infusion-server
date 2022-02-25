package no.ntnu.webdev.webproject7.comment

import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, CommentId>