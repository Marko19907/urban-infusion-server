package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class CommentUpdateDTO(
    /**
     * The ID of the Comment to update.
     */
    val id: CommentId,
    val text: String
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.id, this.text);
    }
}
