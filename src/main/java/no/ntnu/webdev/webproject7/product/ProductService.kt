package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.crud.CrudService
import org.springframework.stereotype.Service

@Service
class ProductService(productRepository: ProductRepository) :
    CrudService<Product, ProductId>(productRepository) {
}
