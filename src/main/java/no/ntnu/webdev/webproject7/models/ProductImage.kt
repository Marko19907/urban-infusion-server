package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.loadProductImage
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
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

    @Column(nullable = false)
    val extension: String?,

) : CrudModel<ProductImageId> {

    @Lob
    @Column(nullable = true)
    var image: ByteArray? = null;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    var lastModified: LocalDateTime = LocalDateTime.now();

    init {
        if (this.title != null) {
            this.image = loadProductImage(this.title, this.extension);
        }
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.image, this.lastModified);
    }
}
