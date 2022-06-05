package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.dto.CommentDTO
import no.ntnu.webdev.webproject7.dto.CommentUpdateDTO
import no.ntnu.webdev.webproject7.exceptions.CommentException
import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.services.CommentService
import no.ntnu.webdev.webproject7.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("comments")
class CommentController(
    @Autowired private val commentService: CommentService,
    @Autowired private val userService: UserService
) {

    @GetMapping("")
    fun all(): ResponseEntity<List<Comment>> {
        return ResponseEntity(this.commentService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: CommentId): ResponseEntity<Comment> {
        val entity = this.commentService.getById(id);
        return if (entity != null) ResponseEntity(entity, HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun delete(@PathVariable id: CommentId): ResponseEntity<String> {
        val user: User = this.userService.getSessionUser() ?: return ResponseEntity("You must be logged in!", HttpStatus.BAD_REQUEST);
        return if (this.commentService.delete(id, user)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    fun addOne(@RequestBody comment: CommentDTO): ResponseEntity<String> {
        return try {
            val user: User? = this.userService.getSessionUser();
            if (this.commentService.add(comment, user)) ResponseEntity(HttpStatus.OK)
            else ResponseEntity("Failed to add a comment, reason unknown", HttpStatus.BAD_REQUEST);
        } catch (e: CommentException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('USER')")
    fun update(@RequestBody comment: CommentUpdateDTO): ResponseEntity<String> {
        return try {
            val user: User? = this.userService.getSessionUser();
            if (this.commentService.update(comment, user)) ResponseEntity(HttpStatus.OK)
            else ResponseEntity("Failed to update a comment, reason unknown", HttpStatus.BAD_REQUEST);
        } catch (e: CommentException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
    }
}
