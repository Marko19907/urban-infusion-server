package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class ProductUpdateDTO(
    val productId: ProductId,
    override val title: String,
    override val price: Double,
    override val discount: Double,
    override val description: String,
    override val weight: String,
    override val category: Category
) : ProductDTO(title, price, discount, description, weight, category) {

    override fun validate(): Boolean {
        return super.validate() && objectsNotNull(this.productId);
    }
}
