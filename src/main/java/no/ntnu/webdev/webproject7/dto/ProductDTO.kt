package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

private const val descriptionLength = 1000;

class ProductDTO(
    val title: String,
    val price: Double,
    val discount: Double,
    val description: String,
    val weight: String,
    val category: Category,
): DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.title, this.price, this.discount, this.description, this.weight, this.category)
                && this.description.length <= descriptionLength;
    }
}
