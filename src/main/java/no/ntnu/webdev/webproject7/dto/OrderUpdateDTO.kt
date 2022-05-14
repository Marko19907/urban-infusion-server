package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.OrderStatus
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class OrderUpdateDTO(
    val orderId: OrderId,
    val status: OrderStatus
) : DTO {

    override fun validate(): Boolean {
        return this.orderId > 0 && objectsNotNull(this.orderId, this.status);
    }
}
