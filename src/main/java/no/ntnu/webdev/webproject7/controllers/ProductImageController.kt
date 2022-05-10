package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.ProductImage
import no.ntnu.webdev.webproject7.models.ProductImageId
import no.ntnu.webdev.webproject7.services.ProductImageService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("product-images")
class ProductImageController(private val productImageService: ProductImageService) {

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: ProductImageId): ResponseEntity<ByteArray> {
        val response: ResponseEntity<ByteArray>
        val image: ProductImage? = this.productImageService.getById(id)
        response = if (image != null) {
            ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .body(image.image)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return response
    }
}
