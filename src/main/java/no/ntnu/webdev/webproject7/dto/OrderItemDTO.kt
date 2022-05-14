package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class OrderItemDTO(
    val productId: ProductId,
    val quantity: Int
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.productId, this.quantity)
                && this.productId > 0
                && this.quantity > 0;
    }
}
