package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

open class ProductUpdatePartialDTO(
    val productId: ProductId,
    val title: String?,
    val price: Double?,
    val discount: Double?,
    val description: String?,
    val weight: String?,
    val category: Category
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.productId, this.category);
    }
}
