package no.ntnu.webdev.webproject7.category

import no.ntnu.webdev.webproject7.product.Category
import no.ntnu.webdev.webproject7.product.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("categories")
class CategoryController(
    private val productRepository: ProductRepository
) {
    @GetMapping("")
    fun all(): ResponseEntity<MutableSet<Category>> {
        return ResponseEntity(getCategoryMap(), HttpStatus.OK)
    }

    private fun getCategoryMap(): MutableSet<Category> {
        val map: MutableSet<Category> = mutableSetOf();
        productRepository.findAll().toList()
            .filterNotNull()
            .forEach { product ->
                run {
                    map.add(product.category!!);
                }
            }
        return map;
    }
}
