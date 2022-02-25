package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.crud.CrudModel
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

typealias ProductId = String

@Entity
class Product(
    @Id
    @Column(nullable = false)
    override var id: ProductId? = null,

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

    override fun validate(): Boolean {
        return objectsNotNull(id, discount, image, title, description, weight);
    }
}
