package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.comment.Comment
import no.ntnu.webdev.webproject7.crud.CrudModel
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.*

typealias ProductId = Long

enum class Category(val type: String){
    TEA("tea"),
    ACCESSORIES("accessories")
}

enum class Subcategory(val type: String){
    BLACK_TEA("black tea"),
    GREEN_TEA("green tea"),
    WHITE_TEA("white tea"),
    CUP("cups")
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

    @Column(nullable = false)
    var description: String? = null,

    @Column(nullable = false)
    var weight: String? = null,

    @Column(nullable = false)
    val category: Category? = null,

    @Column(nullable = false)
    val subcategory: Subcategory? = null

) : CrudModel<ProductId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: ProductId = 0

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(discount, price, title, description, weight, category, subcategory);
    }
}
