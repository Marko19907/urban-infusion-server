package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.loadImage
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table

typealias ProductImageId = Int;

@Entity
@Table(name = "productImage")
open class ProductImage(

    @Id
    override var id: ProductImageId = 0,

    @Column(nullable = true)
    val title: String?,

) : CrudModel<ProductImageId> {

    @Lob
    @Column(nullable = true)
    var image: ByteArray? = null;

    init {
        if (this.title != null) {
            this.image = loadImage(this.title);
        }
    }

    protected constructor() : this(0, "");

    override fun validate(): Boolean {
        return objectsNotNull(this.image);
    }
}
