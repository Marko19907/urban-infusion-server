package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

open class ProductUpdatePartialDTO(
    /**
     * ID of the Product to update.
     */
    val id: ProductId,
    val title: String?,
    val price: Double?,
    val discount: Double?,
    val description: String?,
    val weight: String?,
    val category: String?
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.id);
    }
}
