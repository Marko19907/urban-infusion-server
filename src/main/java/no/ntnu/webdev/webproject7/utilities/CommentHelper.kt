package no.ntnu.webdev.webproject7.utilities

import no.ntnu.webdev.webproject7.dto.CommentDTO
import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import no.ntnu.webdev.webproject7.services.CommentService
import no.ntnu.webdev.webproject7.services.ProductService
import org.springframework.stereotype.Component

@Component
class CommentHelper(
    private val commentService: CommentService,
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
        this.commentService.add(comment);

        val product = this.productService.getById(commentDTO.productID);
        if (product == null || !product.validate()) {
            return false;
        }

        val newProduct: Product = this.mergeCommentProduct(product, comment);
        return this.productRepository.save(newProduct).id == product.id;
    }

    private fun mergeCommentProduct(product: Product, comment: Comment): Product {
        val commentList: MutableList<Comment> = product.comments;
        commentList.add(comment);
        product.comments = commentList;
        return product;
    }

    private fun createComment(commentDTO: CommentDTO, user: User): Comment {
        return Comment(user, commentDTO.text, null);
    }
}
