package no.ntnu.webdev.webproject7.user

import no.ntnu.webdev.webproject7.crud.CrudModel
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.*

typealias UserEntityId = Long;

@Entity
class UserEntity(
    @Column(nullable = false)
    var admin: Boolean? = null,

    @Column(nullable = false)
    var email: String? = null,

    @Column(nullable = false)
    var password: String? = null

) : CrudModel<UserEntityId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: UserEntityId = 0

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(admin, email, password);
    }
}
