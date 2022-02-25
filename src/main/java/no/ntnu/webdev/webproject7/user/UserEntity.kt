package no.ntnu.webdev.webproject7.user

import no.ntnu.webdev.webproject7.crud.CrudModel
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity(
    @Id override var id: String,
    var admin: Boolean,
    var email: String,
    var password: String
) : CrudModel<String> {
   protected constructor() : this("", false, "", "");
}