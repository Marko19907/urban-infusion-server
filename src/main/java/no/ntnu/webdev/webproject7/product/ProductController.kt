package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.crud.CrudController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(private val productService: ProductService) : CrudController<Product, ProductId>(productService) {

    @GetMapping("/categories")
    fun getCategories(): ResponseEntity<MutableSet<Category>> {
        return ResponseEntity(productService.getCategoryMap(), HttpStatus.OK);
    }
}
