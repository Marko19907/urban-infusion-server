package no.ntnu.webdev.webproject7.utilities

import no.ntnu.webdev.webproject7.dto.CommentDTO
import no.ntnu.webdev.webproject7.dto.CommentUpdateDTO
import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommentHelper(
    private val commentRepository: CommentRepository,
    private val productRepository: ProductRepository
) {

    fun add(commentDTO: CommentDTO?, user: User?): Boolean {
        if (commentDTO == null || user == null) {
            return false;
        }
        if (!commentDTO.validate() || !user.validate()) {
            return false;
        }
        val comment = Comment(user, commentDTO.text, null);
        val product = this.productRepository.findByIdOrNull(commentDTO.productID) ?: return false;

        product.comments.add(comment);
        return this.productRepository.save(product).id == product.id;
    }

    fun update(commentDTO: CommentUpdateDTO?, user: User?): Boolean {
        if (commentDTO == null || user == null) {
            return false;
        }
        if (!commentDTO.validate() || !user.validate()) {
            return false;
        }

        val comment = this.commentRepository.findByIdOrNull(commentDTO.commentId) ?: return false;
        val product = this.productRepository.findByIdOrNull(commentDTO.productID) ?: return false;
        if (comment.user?.equals(user) == false || !product.containsCommentWithID(commentDTO.commentId)) {
            return false;
        }

        comment.text = commentDTO.text;

        this.commentRepository.save(comment);
        return this.commentRepository.findByIdOrNull(comment.id) != null;
    }

    fun delete(id: CommentId): Boolean {
        val comment = this.commentRepository.findByIdOrNull(id) ?: return false;
        val product = this.productRepository.findProductByCommentsId(id) ?: return false;

        product.removeComment(comment);
        this.commentRepository.deleteById(comment.id);

        return this.productRepository.findProductByCommentsId(id) == null
                && this.commentRepository.findByIdOrNull(id) == null;
    }
}
