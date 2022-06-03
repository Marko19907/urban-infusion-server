package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.dto.ProductDTO
import no.ntnu.webdev.webproject7.dto.ProductUpdateDTO
import no.ntnu.webdev.webproject7.dto.ProductUpdatePartialDTO
import no.ntnu.webdev.webproject7.exceptions.ProductException
import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.services.ProductDeletionService
import no.ntnu.webdev.webproject7.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(
    @Autowired private val productService: ProductService,
    @Autowired private val productDeletionService: ProductDeletionService
) {

    @GetMapping("")
    fun all(): ResponseEntity<List<Product>> {
        return ResponseEntity(this.productService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: ProductId): ResponseEntity<Product> {
        val entity = this.productService.getById(id);
        return if (entity != null) ResponseEntity(entity, HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun delete(@PathVariable id: ProductId): ResponseEntity<String> {
        return if (this.productDeletionService.delete(id)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun addOne(@RequestBody productDTO: ProductDTO): ResponseEntity<String> {
        return try {
            if (this.productService.add(productDTO)) ResponseEntity(HttpStatus.OK)
            else ResponseEntity("Failed to add a product, reason unknown", HttpStatus.BAD_REQUEST);
        } catch (e: ProductException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun update(@RequestBody productUpdateDTO: ProductUpdateDTO): ResponseEntity<String> {
        return try {
            if (this.productService.update(productUpdateDTO)) ResponseEntity(HttpStatus.OK)
            else ResponseEntity("Failed to update a product, reason unknown", HttpStatus.BAD_REQUEST);
        } catch (e: ProductException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun updatePartial(@RequestBody productUpdatePartialDTO: ProductUpdatePartialDTO): ResponseEntity<String> {
        return try {
            if (this.productService.update(productUpdatePartialDTO)) ResponseEntity(HttpStatus.OK)
            else ResponseEntity("Failed to update a product, reason unknown", HttpStatus.BAD_REQUEST);
        } catch (e: ProductException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/categories")
    fun getCategories(): ResponseEntity<MutableSet<String>> {
        return ResponseEntity(this.productService.getCategoryMap(), HttpStatus.OK);
    }

    @GetMapping("/categories/{category}")
    fun getOneByCategory(@PathVariable category: String): ResponseEntity<List<Product>> {
        return ResponseEntity(this.productService.getByCategory(category.lowercase()), HttpStatus.OK);
    }
}
