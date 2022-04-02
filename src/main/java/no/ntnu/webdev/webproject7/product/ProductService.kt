package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.crud.CrudService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired productRepository: ProductRepository) : CrudService<Product, ProductId>(productRepository) {

    fun getCategoryMap(): MutableSet<Category> {
        return Category.values().toHashSet();
    }
}
