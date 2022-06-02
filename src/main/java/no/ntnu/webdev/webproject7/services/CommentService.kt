package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.CommentDTO
import no.ntnu.webdev.webproject7.dto.CommentUpdateDTO
import no.ntnu.webdev.webproject7.exceptions.CommentException
import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.models.MAX_COMMENT_LENGTH
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val productRepository: ProductRepository
) : CrudService<Comment, CommentId>(commentRepository) {

    @Throws(CommentException::class)
    fun add(commentDTO: CommentDTO, user: User?): Boolean {
        if (user == null) {
            throw CommentException("Incorrect login, you must be logged in to comment!");
        }
        if (!commentDTO.validate() || !user.validate()) {
            throw CommentException("The request is incorrectly formatted!");
        }
        this.verifyCommentLength(commentDTO.text);

        val product = this.productRepository.findByIdOrNull(commentDTO.id)
            ?: throw CommentException("A product with the id: ${commentDTO.id} does not exist!");
        val comment = Comment(user, commentDTO.text, null);

        product.comments.add(comment);
        return this.productRepository.save(product).id == product.id;
    }

    @Throws(CommentException::class)
    fun update(commentDTO: CommentUpdateDTO, user: User?): Boolean {
        if (user == null) {
            throw CommentException("Incorrect login, you must be logged in to comment!");
        }
        if (!commentDTO.validate() || !user.validate()) {
            throw CommentException("The request is incorrectly formatted!");
        }
        this.verifyCommentLength(commentDTO.text);

        val comment = this.commentRepository.findByIdOrNull(commentDTO.id)
            ?: throw CommentException("A comment with the id: ${commentDTO.id} does not exist!");
        if (user != comment.user) {
            throw CommentException("You must be an author of the comment you are trying to edit!");
        }

        comment.text = commentDTO.text;

        this.commentRepository.save(comment);
        return this.commentRepository.findByIdOrNull(comment.id) != null;
    }

    override fun delete(id: CommentId): Boolean {
        val comment = this.commentRepository.findByIdOrNull(id) ?: return false;
        val product = this.productRepository.findProductByCommentsId(id) ?: return false;

        product.removeComment(comment);
        this.commentRepository.deleteById(comment.id);

        return this.productRepository.findProductByCommentsId(id) == null
                && this.commentRepository.findByIdOrNull(id) == null;
    }

    @Throws(CommentException::class)
    private fun verifyCommentLength(commentText: String) {
        if (commentText.length > MAX_COMMENT_LENGTH) {
            throw CommentException("A comment can not be longer than $MAX_COMMENT_LENGTH characters!");
        }
    }
}
