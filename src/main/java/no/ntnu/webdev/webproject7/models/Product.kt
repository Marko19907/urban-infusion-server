package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinTable
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

typealias ProductId = Long;

const val MAX_DESCRIPTION_LENGTH = 1000;

@Entity
@Table(name = "product")
open class Product(

    @JoinTable
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval=true, cascade = [CascadeType.ALL], targetEntity = Comment::class)
    open var comments: MutableList<Comment> = ArrayList(),

    @Positive
    @Column(nullable = false)
    open var price: Double? = null,

    @PositiveOrZero
    @Column(nullable = false)
    open var discount: Double? = null,

    @Column(nullable = true)
    open var imageId: Int? = null,

    @Column(nullable = false)
    open var title: String? = null,

    @Column(nullable = false, length = MAX_DESCRIPTION_LENGTH)
    open var description: String? = null,

    @Column(nullable = false)
    open var weight: String? = null,

    @Column(nullable = false)
    open var category: String,

    ) : CrudModel<ProductId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: ProductId = 0;

    /**
     * Removes the given Comment.
     */
    fun removeComment(comment: Comment) {
        this.comments.removeIf { it.id == comment.id };
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.discount, this.price, this.title, this.description, this.weight, this.category);
    }
}
