package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Lob
import javax.persistence.MappedSuperclass

/**
 * The maximum size of the image in bytes, currently 10MB.
 */
const val MAX_IMAGE_SIZE = 10485760;

@MappedSuperclass
abstract class ImageModel<ID>(

    id: ID?,

    @Column(nullable = true)
    val title: String?,

    @Column(nullable = false)
    var extension: String?,

) : CrudModel<ID> {

    @Lob
    @Column(nullable = true, length = MAX_IMAGE_SIZE)
    var image: ByteArray? = null;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    var lastModified: LocalDateTime = LocalDateTime.now();

    init {
        this.loadImage();
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.image, this.lastModified);
    }

    abstract fun loadImage();
}
