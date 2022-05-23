package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.loadUserImage
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table

typealias UserImageId = Int;

@Entity
@Table(name = "userImage")
class UserImage(

    @Id
    override var id: UserImageId = 0,

    @Column(nullable = true)
    val title: String?,

    @Column(nullable = false)
    val extension: String?,

    ) : CrudModel<UserImageId> {

    @Lob
    @Column(nullable = true)
    var image: ByteArray? = null;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    var lastModified: LocalDateTime = LocalDateTime.now();

    init {
        if (this.title != null) {
            this.image = loadUserImage(this.title, this.extension);
        }
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.image, this.lastModified);
    }
}
