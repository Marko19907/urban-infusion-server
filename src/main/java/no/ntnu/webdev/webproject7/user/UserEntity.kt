package no.ntnu.webdev.webproject7.user

import no.ntnu.webdev.webproject7.crud.CrudModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

typealias UserEntityId = String;

@Entity
class UserEntity(
    @Id
    @Column(nullable = false)
    override var id: UserEntityId? = null,

    @Column(nullable = false)
    var admin: Boolean? = null,

    @Column(nullable = false)
    var email: String? = null,

    @Column(nullable = false)
    var password: String? = null
) : CrudModel<UserEntityId> {

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return arrayOf(id, admin, email, password).all { e -> e != null }
    }
}
