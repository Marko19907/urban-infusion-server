package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.loadProductImage
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

typealias ProductImageId = Int;

@Entity
@Table(name = "productImage")
class ProductImage(

    @Id
    override var id: ProductImageId = 0,

    title: String?,

    extension: String?,

) : ImageModel<ProductImageId>(title, extension) {

    override fun loadImage() {
        if (this.title != null) {
            this.image = loadProductImage(this.title, this.extension);
        }
    }
}
