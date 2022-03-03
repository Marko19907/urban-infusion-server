package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.comment.Comment
import no.ntnu.webdev.webproject7.crud.CrudModel
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.*

typealias ProductId = String

@Entity
class Product(
    @Id
    @Column(nullable = false)
    override var id: ProductId? = null,

    @OneToMany
    @JoinColumn(name = "comment_id")
    var comments: MutableList<Comment>? = mutableListOf(),

    @Column(nullable = false)
    var price: Double? = null,

    @Column(nullable = false)
    var discount: Double? = null,

    var image: String? = null,

    @Column(nullable = false)
    var title: String? = null,

    @Column(nullable = false)
    var description: String? = null,

    @Column(nullable = false)
    var weight: String? = null

) : CrudModel<ProductId> {

    protected constructor() : this(null)

    fun add(comment: Comment?) {
        if (comment != null) {
            comments?.add(comment)
        };
    }

    fun getAll(): MutableList<Comment>? {
        return this.comments;
    }

    override fun validate(): Boolean {
        return objectsNotNull(id, discount, image, title, description, weight);
    }
}
