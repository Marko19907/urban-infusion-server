package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.services.CommentService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("comments")
class CommentController(commentService: CommentService) : CrudController<Comment, CommentId>(commentService)
