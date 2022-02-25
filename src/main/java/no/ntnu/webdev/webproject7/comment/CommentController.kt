package no.ntnu.webdev.webproject7.comment

import no.ntnu.webdev.webproject7.crud.CrudController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("comments")
class CommentController(commentService: CommentService) : CrudController<Comment, CommentId>(commentService)
