package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.dto.CommentDTO
import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.services.CommentService
import no.ntnu.webdev.webproject7.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("comments")
class CommentController(
    private val commentService: CommentService,
    private val userService: UserService,
) : CrudController<Comment, CommentId>(commentService) {

    @Override
    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(params = ["type=1"])
    fun addOne(@RequestBody comment: CommentDTO): ResponseEntity<String> {
        // TODO: Spring does not allow overriding of functions that use annotations, adding a type=1
        //  The URL is therefore /comments?type=1 for now . . .
        val user: User? = this.userService.getSessionUser();
        println(user.toString());
        println(user?.username);
        return if (this.commentService.add(comment, user)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
