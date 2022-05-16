package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class CommentUpdateDTO(
    val commentId: CommentId,
    override val productID: ProductId,
    override val text: String
) : CommentDTO(productID, text) {

    override fun validate(): Boolean {
        return super.validate() && objectsNotNull(this.commentId);
    }
}
