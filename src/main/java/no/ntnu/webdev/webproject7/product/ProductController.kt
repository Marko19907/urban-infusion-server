package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.crud.CrudController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(productService: ProductService) : CrudController<Product, ProductId>(productService) {

    @GetMapping("/categories")
    fun getCategories(): ResponseEntity<MutableSet<Category>> {
        return ResponseEntity(getCategoryMap(), HttpStatus.OK);
    }

    private fun getCategoryMap(): MutableSet<Category> {
        return Category.values().toHashSet();
    }
}
