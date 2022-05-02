package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

typealias ProductId = Long

enum class Category(val type: String){
    TEA("tea"),
    ACCESSORIES("accessories")
}

@Entity
@Table(name = "product")
open class Product(

    @Column(nullable = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], targetEntity=Comment::class)
    open var comments: List<Comment> = ArrayList(),

    @Column(nullable = false)
    open var price: Double? = null,

    @Column(nullable = false)
    open var discount: Double? = null,

    @Column(nullable = true)
    open var image: String? = null,

    @Column(nullable = false)
    open var title: String? = null,

    @Column(nullable = false, length = 1000)
    open var description: String? = null,

    @Column(nullable = false)
    open var weight: String? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    open val category: Category? = null,

    ) : CrudModel<ProductId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: ProductId = 0

    protected constructor() : this(mutableListOf())

    override fun validate(): Boolean {
        return objectsNotNull(this.discount, this.price, this.title, this.description, this.weight, this.category);
    }
}
