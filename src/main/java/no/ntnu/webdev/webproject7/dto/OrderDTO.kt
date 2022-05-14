package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class OrderDTO(
    val userId: UserEntityId,
    val products: List<OrderItemDTO>
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.userId, this.products)
                && this.userId > 0
                && this.products.isNotEmpty()
                && this.products.none { !it.validate() }
    }
}
