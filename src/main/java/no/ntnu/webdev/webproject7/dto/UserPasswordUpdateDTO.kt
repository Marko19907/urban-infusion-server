package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class UserPasswordUpdateDTO(
    val password: String
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.password);
    }
}
