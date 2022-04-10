package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.*

typealias UserEntityId = Long;

enum class Role {
    USER,
    ADMIN
}

@Entity
open class UserEntity(
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

) : CrudModel<UserEntityId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: UserEntityId = 0

    @Column(nullable = false)
    private var role: Role = Role.USER;

    protected constructor() : this(null)

    fun setRole(role: Role) {
        this.role = role;
    }

    fun getRole(): Role {
        return this.role;
    }

    override fun validate(): Boolean {
        return objectsNotNull(role, email, username, password, city, zipcode, phone_number, address);
    }
}
