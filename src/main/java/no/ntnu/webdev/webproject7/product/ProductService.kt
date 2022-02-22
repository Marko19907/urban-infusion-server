package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.utilities.iterableToList
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    val allProducts: List<Product>
        get() = iterableToList(productRepository.findAll())

    fun getProductById(id: String?): Product? {
        // Guard condition
        if (id == null) {
            return null
        }
        val result = productRepository.findById(id)
        return result.orElse(null)
    }

    fun addProduct(product: Product?): Boolean {
        if (product == null || getProductById(product.id) != null) {
            return false
        }
        val saved = productRepository.save(product)
        return product.id == saved.id
    }

    fun updateProduct(product: Product?): Boolean {
        if (product == null) {
            return false
        }
        productRepository.save(product)
        return true
    }

    fun deleteProduct(id: String?): Boolean {
        if (id == null) {
            return false
        }
        productRepository.deleteById(id)
        return getProductById(id) == null
    }
}