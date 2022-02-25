package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.utilities.iterableToList
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    val allProducts: List<Product>
        get() = iterableToList(productRepository.findAll())

    fun getProductById(id: String): Product? {
        return productRepository.findById(id).orElse(null)
    }

    fun addProduct(product: Product): Boolean {
        if (product.id == null || getProductById(product.id) != null) {
            return false
        }
        return productRepository.save(product).id == product.id
    }

    fun updateProduct(product: Product): Boolean {
        if (product.id == null) return false;
        productRepository.save(product)
        return true
    }

    fun deleteProduct(id: String): Boolean {
        productRepository.deleteById(id)
        return getProductById(id) == null
    }
}