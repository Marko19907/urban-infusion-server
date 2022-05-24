package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.ProductImage
import no.ntnu.webdev.webproject7.models.ProductImageId
import no.ntnu.webdev.webproject7.repositories.ProductImageRepository
import org.springframework.stereotype.Service

@Service
class ProductImageService(
    productImageRepository: ProductImageRepository
) : ImageService<ProductImage, ProductImageId>(productImageRepository) {

    override fun createEntity(id: ProductImageId): ProductImage {
        return ProductImage(id);
    }
}
