package no.ntnu.webdev.webproject7.comment

import no.ntnu.webdev.webproject7.crud.CrudModel
import no.ntnu.webdev.webproject7.product.Product
import no.ntnu.webdev.webproject7.user.UserEntity
import java.time.LocalDate
import javax.persistence.*

typealias CommentId = String;

@Entity
class Comment(
    @Id
    @Column(nullable = false)
    override var id: CommentId? = null,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @Column(nullable = false)
    var text: String? = null,

    @Column(nullable = false)
    var date: LocalDate? = null
) : CrudModel<CommentId> {
    protected constructor() : this(null)

    override fun validate(): Boolean {
        return arrayOf(id, text, date).any { e -> e != null }
    }
}