package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

data class CommentDTO(
    val productID: ProductId,
    val text: String
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.text, this.productID) && this.text.length <= 1000;
    }
}
