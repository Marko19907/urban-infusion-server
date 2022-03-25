package no.ntnu.webdev.webproject7.category

import no.ntnu.webdev.webproject7.product.Category
import no.ntnu.webdev.webproject7.product.ProductRepository
import no.ntnu.webdev.webproject7.product.Subcategory
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
    fun all(): ResponseEntity<MutableMap<Category?, MutableSet<Subcategory?>>> {
        return ResponseEntity(
            getCategoryMap()
            // Category.values().toList()
            , HttpStatus.OK)
    }

    private fun getCategoryMap(): MutableMap<Category?, MutableSet<Subcategory?>> {
        var map: MutableMap<Category?, MutableSet<Subcategory?>> = mutableMapOf();
        productRepository.findAll().forEach { product ->
            run {
                if (map.get(product.category) == null) {
                    map.put(product.category, mutableSetOf(product.subcategory))
                }
                else {
                    map.get(product.category)!!.add(product.subcategory)
                }
            }
        }
        return map;
    }
}