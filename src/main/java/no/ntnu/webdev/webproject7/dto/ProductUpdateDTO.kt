package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class ProductUpdateDTO(
    /**
     * ID of the Product to update.
     */
    id: ProductId,
    title: String,
    price: Double,
    discount: Double,
    description: String,
    weight: String,
    category: String
) : ProductUpdatePartialDTO(id, title, price, discount, description, weight, category) {

    override fun validate(): Boolean {
        return objectsNotNull(
            this.id, this.title, this.price, this.discount, this.description, this.weight, this.category
        );
    }
}
