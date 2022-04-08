package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import no.ntnu.webdev.webproject7.services.CrudService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ProductService(@Autowired productRepository: ProductRepository) :
    CrudService<Product, ProductId>(productRepository) {
    fun getCategoryMap(): MutableSet<Category> {
        return Category.values().toHashSet();
    }
}
