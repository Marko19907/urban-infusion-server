package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class ProductUpdateDTO(
    productId: ProductId,
    title: String,
    price: Double,
    discount: Double,
    description: String,
    weight: String,
    category: Category
) : ProductUpdatePartialDTO(productId, title, price, discount, description, weight, category) {

    override fun validate(): Boolean {
        return objectsNotNull(
            this.productId, this.title, this.price, this.discount, this.description, this.weight, this.category
        );
    }
}
