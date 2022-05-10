package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.ProductImage
import no.ntnu.webdev.webproject7.models.ProductImageId
import no.ntnu.webdev.webproject7.services.ProductImageService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("product-images")
class ProductImageController(productImageService: ProductImageService) : CrudController<ProductImage, ProductImageId>(productImageService)
