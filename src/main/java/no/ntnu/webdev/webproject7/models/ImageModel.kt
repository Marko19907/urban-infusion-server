package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Lob
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class ImageModel<ID>(

    id: ID?,

    @Column(nullable = true)
    val title: String?,

    @Column(nullable = false)
    var extension: String?,

) : CrudModel<ID> {

    @Lob
    @Column(nullable = true)
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
