package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentService(@Autowired commentRepository: CommentRepository) : CrudService<Comment, CommentId>(commentRepository)