package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.Comment
import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import no.ntnu.webdev.webproject7.utilities.CommentHelper
import org.springframework.stereotype.Service

@Service
class CommentService(
    commentRepository: CommentRepository,
    private val commentHelper: CommentHelper
) : CrudService<Comment, CommentId>(commentRepository) {

    override fun delete(id: CommentId): Boolean {
        return this.commentHelper.delete(id);
    }
}
