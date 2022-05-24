package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.loadUserImage
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

typealias UserImageId = Int;

@Entity
@Table(name = "userImage")
class UserImage(

    @Id
    override var id: UserImageId = 0,

    title: String?,

    extension: String?,

) : ImageModel<UserImageId>(id, title, extension) {

    constructor(id: ProductImageId) : this(id, null, null);

    override fun loadImage() {
        if (this.title != null) {
            this.image = loadUserImage(this.title, this.extension);
        }
    }
}
