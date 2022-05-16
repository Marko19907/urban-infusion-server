package no.ntnu.webdev.webproject7.utilities

import no.ntnu.webdev.webproject7.dto.CommentDTO
import no.ntnu.webdev.webproject7.dto.CommentUpdateDTO
import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import no.ntnu.webdev.webproject7.services.ProductService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommentHelper(
    private val commentRepository: CommentRepository,
    private val productService: ProductService,
    private val productRepository: ProductRepository
) {

    fun add(commentDTO: CommentDTO?, user: User?): Boolean {
        if (commentDTO == null || user == null) {
            return false;
        }
        if (!commentDTO.validate() || !user.validate()) {
            return false;
        }
        val comment = this.createComment(commentDTO, user);
        this.commentRepository.save(comment);

        val product = this.productService.getById(commentDTO.productID);
        if (product == null || !product.validate()) {
            return false;
        }

        val newProduct: Product = this.mergeCommentProduct(product, comment);
        return this.productRepository.save(newProduct).id == product.id;
    }

    private fun mergeCommentProduct(product: Product, comment: Comment): Product {
        val commentList: MutableList<Comment> = product.comments.toMutableList();
        commentList.add(comment);
        product.comments = commentList;
        return product;
    }

    private fun createComment(commentDTO: CommentDTO, user: User): Comment {
        return Comment(user, commentDTO.text, null);
    }

    fun update(commentDTO: CommentUpdateDTO?, user: User?): Boolean {
        if (commentDTO == null || user == null) {
            return false;
        }
        if (!commentDTO.validate() || !user.validate()) {
            return false;
        }

        val comment = this.commentRepository.findByIdOrNull(commentDTO.commentId) ?: return false;
        val product = this.productService.getById(commentDTO.productID) ?: return false;
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

        val commentList: MutableList<Comment> = product.comments.toMutableList();
        commentList.removeIf { it.id == comment.id };
        product.comments = commentList;

        this.commentRepository.deleteById(comment.id);
        this.productRepository.save(product);

        return this.productRepository.findProductByCommentsId(id) == null
                && this.commentRepository.findByIdOrNull(id) == null;
    }
}
