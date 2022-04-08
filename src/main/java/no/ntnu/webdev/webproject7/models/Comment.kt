package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import java.time.LocalDate
import javax.persistence.*

typealias CommentId = Long;

@Entity
open class Comment(
    @OneToOne
    var user: UserEntity? = null,

    @Column(nullable = false)
    var text: String? = null,

    @Column(nullable = true)
    var lastUpdated: LocalDate? = null

) : CrudModel<CommentId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: CommentId = 0

    @Column(nullable = false)
    var created: LocalDate = LocalDate.now()

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(text); // TODO: The date is not being checked for null!
    }
}