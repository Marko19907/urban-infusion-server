package no.ntnu.webdev.webproject7.product

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("products")
class ProductController(private val productService: ProductService) {

    @get:GetMapping("")
    val all: ResponseEntity<List<Product>>
        get() = ResponseEntity(productService.allProducts, HttpStatus.OK)

    @PostMapping("")
    fun addOne(@RequestBody product: Product?): ResponseEntity<String> {
        return if (productService.addProduct(product)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PutMapping("")
    fun update(@RequestBody product: Product?): ResponseEntity<String> {
        return if (productService.updateProduct(product)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String?): ResponseEntity<String> {
        return if (productService.deleteProduct(id)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}