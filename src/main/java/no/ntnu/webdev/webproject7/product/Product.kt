package no.ntnu.webdev.webproject7.product

import no.ntnu.webdev.webproject7.crud.CrudModel
import javax.persistence.Entity
import javax.persistence.Id

typealias ProductId = String

@Entity
class Product : CrudModel<ProductId> {
    @Id
    override var id: ProductId? = null
    var price: Double? = null
    var discount: Double? = null
    var image: String? = null
    var title: String? = null
    var description: String? = null
    var weight: String? = null

    constructor(
        id: ProductId?,
        price: Double?,
        discount: Double?,
        image: String?,
        title: String?,
        description: String?,
        weight: String?
    ) {
        this.id = id
        this.price = price
        this.discount = discount
        this.image = image
        this.title = title
        this.description = description
        this.weight = weight
    }

    protected constructor()
}
