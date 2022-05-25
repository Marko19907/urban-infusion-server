package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.OrderStatus
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class OrderUpdateDTO(
    val id: OrderId,
    val status: OrderStatus
) : DTO {

    override fun validate(): Boolean {
        return this.id > 0 && objectsNotNull(this.id, this.status);
    }
}
