package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import no.ntnu.webdev.webproject7.utilities.ProductHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ProductService(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val productHelper: ProductHelper
) : CrudService<Product, ProductId>(productRepository) {

    override fun delete(id: ProductId): Boolean {
        return this.productHelper.deleteProduct(id);
    }

    fun getCategoryMap(): MutableSet<Category> {
        return Category.values().toHashSet();
    }

    fun getByCategory(category: String): List<Product> {
        return this.all().stream()
            .filter { it.category!!.name.lowercase() == category }
            .toList();
    }
}
