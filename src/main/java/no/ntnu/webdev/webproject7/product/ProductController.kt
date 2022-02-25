package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.crud.CrudController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(productService: ProductService) : CrudController<Product, ProductId>(productService)
