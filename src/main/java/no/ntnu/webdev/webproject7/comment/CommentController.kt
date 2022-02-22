package no.ntnu.webdev.webproject7.comment

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("comments")
class CommentController(private val commentService: CommentService) {

    @get:GetMapping("")
    val allComments: ResponseEntity<List<Comment>>
        get() = ResponseEntity(commentService.allComments, HttpStatus.OK)

    @PostMapping("")
    fun addOne(@RequestBody comment: Comment): ResponseEntity<String> {
        return if (commentService.addComment(comment)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PutMapping("")
    fun update(@RequestBody comment: Comment): ResponseEntity<String> {
        return if (commentService.updateComment(comment)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<String> {
        return if (commentService.deleteComment(id)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}
