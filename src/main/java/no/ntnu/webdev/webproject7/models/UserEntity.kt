package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.*

typealias UserEntityId = Long;

@Entity
open class UserEntity(
    @Column(nullable = false)
    var admin: Boolean? = null,

    @Column(nullable = false)
    var email: String? = null,

    @Column(nullable = false)
    var username: String? = null,

    @Column(nullable = false)
    var password: String? = null,

    @Column(nullable = false)
    var city: String? = null,

    @Column(nullable = false)
    var zipcode: String? = null,

    @Column(nullable = false)
    var address: String? = null,

    @Column(nullable = false)
    var phone_number: String? = null,

    @Column(nullable = false)
    var role: Role = Role.USER

) : CrudModel<UserEntityId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: UserEntityId = 0

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(admin, email, username, password, city, zipcode, phone_number, address);
    }
}
