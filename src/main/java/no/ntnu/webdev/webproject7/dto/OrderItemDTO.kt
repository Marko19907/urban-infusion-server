package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class OrderItemDTO(
    /**
     * The ID of the Product to order.
     */
    val id: ProductId,
    val quantity: Int
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.id, this.quantity)
                && this.id > 0
                && this.quantity > 0;
    }
}
