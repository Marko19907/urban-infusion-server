package no.ntnu.webdev.webproject7.models

import com.fasterxml.jackson.annotation.JsonProperty
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.*

typealias UserEntityId = Long;

enum class Role {
    USER,
    ADMIN
}

@Entity
@Table(name = "UserEntity")
open class User(
    @Column(nullable = false)
    var email: String? = null,

    @Column(nullable = false, unique = true)
    var username: String? = null,

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    var role: Role = Role.USER,

) : CrudModel<UserEntityId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: UserEntityId = 0

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(
            this.email,
            this.username,
            this.password,
            this.city,
            this.zipcode,
            this.phone_number,
            this.address
        );
    }
}
