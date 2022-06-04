package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class OrderDTO(
    val products: List<OrderItemDTO>
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.products)
                && this.products.isNotEmpty()
                && this.products.none { !it.validate() }
    }
}
