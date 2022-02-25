package no.ntnu.webdev.webproject7.comment

import no.ntnu.webdev.webproject7.crud.CrudService
import org.springframework.stereotype.Service

@Service
class CommentService(commentRepository: CommentRepository) : CrudService<Comment, CommentId>(commentRepository)