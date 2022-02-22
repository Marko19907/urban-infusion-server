package no.ntnu.webdev.webproject7.comment

import no.ntnu.webdev.webproject7.utilities.iterableToList
import org.springframework.stereotype.Service

@Service
class CommentService(private val commentRepository: CommentRepository) {
    fun getCommentById(id: String?): Comment? {
        // Guard condition
        if (id == null) {
            return null
        }
        val result = commentRepository.findById(id)
        return result.orElse(null)
    }

    fun addComment(comment: Comment?): Boolean {
        if (comment == null) {
            return false
        }
        commentRepository.save(comment)
        return getCommentById(comment.id) != null
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