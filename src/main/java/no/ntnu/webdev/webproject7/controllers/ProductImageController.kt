package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.exceptions.ImageUploadException
import no.ntnu.webdev.webproject7.models.ProductImage
import no.ntnu.webdev.webproject7.models.ProductImageId
import no.ntnu.webdev.webproject7.services.ImageService
import no.ntnu.webdev.webproject7.services.ProductImageService
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
    @Autowired private val productImageService: ProductImageService
) : ImageController<ProductImage, ProductImageId>() {

    override val service: ImageService<ProductImage, ProductImageId> = this.productImageService;

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    override fun upload(
        @PathVariable id: ProductImageId,
        @RequestParam("fileContent") multipartFile: MultipartFile?
    ): ResponseEntity<String> {
        return try {
            if (this.service.add(
                    id,
                    multipartFile
                )
            ) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (e: ImageUploadException) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
    }
}
