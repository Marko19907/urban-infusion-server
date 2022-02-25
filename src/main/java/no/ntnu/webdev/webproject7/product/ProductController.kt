package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.crud.CrudController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("products")
class ProductController(productService: ProductService) : CrudController<Product, String>(productService)