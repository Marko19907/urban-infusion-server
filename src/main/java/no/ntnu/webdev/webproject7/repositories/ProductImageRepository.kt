package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.ProductImage
import no.ntnu.webdev.webproject7.models.ProductImageId
import org.springframework.data.repository.CrudRepository

interface ProductImageRepository : CrudRepository<ProductImage, ProductImageId>
