package no.ntnu.webdev.webproject7.utilities

import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProductHelper(
    private val productRepository: ProductRepository,
    private val commentRepository: CommentRepository,
    private val orderRepository: OrderRepository
) {

    fun deleteProduct(id: ProductId): Boolean {
        val product = this.productRepository.findByIdOrNull(id) ?: return false;

        // Remove all orders associated with the product
        this.orderRepository.findAll()
            .filter { it.ordersProducts!!
                .any { ordersProducts -> ordersProducts.product!!.id == id } }
            .forEach { this.orderRepository.deleteById(it.id) };

        // Remove all comments associated with the product
        product.comments
            .map { it.id }
            .forEach { this.commentRepository.deleteById(it) };

        this.productRepository.deleteById(product.id);
        return this.productRepository.findByIdOrNull(id) == null;
    }
}
