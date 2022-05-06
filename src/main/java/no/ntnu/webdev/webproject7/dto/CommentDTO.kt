package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

open class CommentDTO(
    open val productID: ProductId,
    open val text: String
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.text, this.productID) && this.text.length <= 1000;
    }
}
