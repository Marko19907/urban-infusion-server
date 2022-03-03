package no.ntnu.webdev.webproject7.comment

import no.ntnu.webdev.webproject7.crud.CrudModel
import no.ntnu.webdev.webproject7.user.UserEntity
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import java.time.LocalDate
import javax.persistence.*

typealias CommentId = Long;

@Entity
class Comment(
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @Column(nullable = false)
    var text: String? = null,

    var date: LocalDate? = null

) : CrudModel<CommentId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: CommentId = 0

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(text); // TODO: The date is not being checked for null!
    }
}