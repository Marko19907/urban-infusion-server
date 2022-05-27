package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.exceptions.ImageUploadException
import no.ntnu.webdev.webproject7.models.ProductImage
import no.ntnu.webdev.webproject7.models.ProductImageId
import no.ntnu.webdev.webproject7.services.ImageService
import no.ntnu.webdev.webproject7.services.ProductImageService
import no.ntnu.webdev.webproject7.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("product-images")
class ProductImageController(
    @Autowired private val productImageService: ProductImageService,
    @Autowired private val productService: ProductService
) : ImageController<ProductImage, ProductImageId>() {

    override val service: ImageService<ProductImage, ProductImageId> = this.productImageService;

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    override fun upload(
        @PathVariable id: ProductImageId,
        @RequestParam("data") multipartFile: MultipartFile?
    ): ResponseEntity<String> {
        return try {
            if (this.productPresent(id) && this.service.add(id, multipartFile) && this.updateProductImageId(id))
                ResponseEntity(HttpStatus.OK)
            else ResponseEntity("Failed to update the product picture, reason unknown", HttpStatus.BAD_REQUEST);
        } catch (e: ImageUploadException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Returns true if a Product with the given ID is present.
     * @throws ImageUploadException If no image is found
     */
    @Throws(ImageUploadException::class)
    private fun productPresent(id: ProductImageId): Boolean {
        return if (this.productService.getById(id.toLong()) != null) true
            else throw ImageUploadException("Can not update the product picture of a product that does not exist!");
    }

    /**
     * Updates the ProductImageId of the Product
     */
    private fun updateProductImageId(id: ProductImageId): Boolean {
        val product = this.productService.getById(id.toLong()) ?: return false;
        product.imageId = id;
        return this.productService.update(product);
    }
}
