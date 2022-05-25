package no.ntnu.webdev.webproject7.utilities

import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import no.ntnu.webdev.webproject7.repositories.ProductImageRepository
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProductHelper(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    private val productImageRepository: ProductImageRepository
) {

    fun deleteProduct(id: ProductId): Boolean {
        val product = this.productRepository.findByIdOrNull(id) ?: return false;

        this.removeProductImages(product);

        this.removeProductOrders(id);

        this.productRepository.deleteById(product.id);
        return this.productRepository.findByIdOrNull(id) == null;
    }

    /**
     * Removes the product picture associated with the given Product.
     */
    private fun removeProductImages(product: Product) {
        if (product.imageId != null) {
            this.productImageRepository.deleteById(product.imageId!!);
        }
    }

    /**
     * Removes all orders associated with the given Product.
     */
    private fun removeProductOrders(id: ProductId) {
        this.orderRepository.findAll()
            .filter { it.ordersProducts!!
                    .any { ordersProducts -> ordersProducts.product!!.id == id }
            }
            .forEach { this.orderRepository.deleteById(it.id) };
    }
}
