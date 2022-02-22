package no.ntnu.webdev.webproject7.comment

import no.ntnu.webdev.webproject7.utilities.iterableToList
import org.springframework.stereotype.Service

@Service
class CommentService(private val commentRepository: CommentRepository) {
    fun getCommentById(id: String?): Comment? {
        if (id == null) {
            return null
        }
        return commentRepository.findById(id).orElse(null)
    }

    fun addComment(comment: Comment?): Boolean {
        if (comment == null || getCommentById(comment.id) != null) {
            return false
        }
        return commentRepository.save(comment).id == comment.id
    }

    val allComments: List<Comment>
        get() = iterableToList(commentRepository.findAll())

    fun updateComment(comment: Comment?): Boolean {
        if (comment == null) {
            return false
        }
        commentRepository.save(comment)
        return true
    }

    fun deleteComment(id: String?): Boolean {
        if (id == null) {
            return false
        }
        commentRepository.deleteById(id)
        return getCommentById(id) == null
    }
}