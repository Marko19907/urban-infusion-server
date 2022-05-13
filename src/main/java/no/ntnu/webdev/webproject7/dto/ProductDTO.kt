package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

private const val descriptionLength = 1000;

open class ProductDTO(
    open val title: String,
    open val price: Double,
    open val discount: Double,
    open val description: String,
    open val weight: String,
    open val category: Category,
): DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.title, this.price, this.discount, this.description, this.weight, this.category)
                && this.description.length <= descriptionLength;
    }
}
