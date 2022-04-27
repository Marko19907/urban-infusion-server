package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.CommentDTO
import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(private val commentRepository: CommentRepository) : CrudService<Comment, CommentId>(commentRepository) {

    @Override
    fun add(commentDTO: CommentDTO?, user: User?): Boolean {
        if (commentDTO == null || user == null) {
            return false;
        }
        if (!commentDTO.validate() || !user.validate()) {
            return false;
        }
        val comment = this.createComment(commentDTO, user);
        return this.commentRepository.save(comment).validate();
    }

    private fun createComment(commentDTO: CommentDTO, user: User): Comment {
        return Comment(user, commentDTO.text, null);
    }
}
