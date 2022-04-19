package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.*

typealias ProductId = Long

enum class Category(val type: String){
    TEA("tea"),
    ACCESSORIES("accessories")
}

@Entity
open class Product(
    @Column(nullable = true)
    @OneToMany(cascade = [CascadeType.ALL])
    val comments: List<Comment>? = null,

    @Column(nullable = false)
    var price: Double? = null,

    @Column(nullable = false)
    var discount: Double? = null,

    @Column(nullable = true)
    var image: String? = null,

    @Column(nullable = false)
    var title: String? = null,

    @Column(nullable = false, length = 1000)
    var description: String? = null,

    @Column(nullable = false)
    var weight: String? = null,

    @Column(nullable = false)
    val category: Category? = null,


    ) : CrudModel<ProductId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: ProductId = 0

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(this.discount, this.price, this.title, this.description, this.weight, this.category);
    }
}
