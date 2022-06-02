package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

open class CommentDTO(
    /**
     * ID of the Product to add the Comment to.
     */
    open val id: ProductId,
    open val text: String
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.text, this.id);
    }
}
