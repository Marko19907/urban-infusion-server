package no.ntnu.webdev.webproject7.models

import com.fasterxml.jackson.annotation.JsonProperty
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

typealias UserEntityId = Long;

enum class Role {
    USER,
    ADMIN
}

@Entity
@Table(name = "UserEntity")
open class User(
    @Email
    @NotBlank
    @Column(nullable = false)
    var email: String? = null,

    @NotBlank
    @Column(nullable = false, unique = true)
    var username: String? = null,

    @NotBlank
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

    @NotBlank
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER,

) : CrudModel<UserEntityId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: UserEntityId = 0;

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
