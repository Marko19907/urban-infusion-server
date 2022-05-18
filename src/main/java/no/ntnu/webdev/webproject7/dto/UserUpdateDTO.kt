package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class UserUpdateDTO(
    val email: String,
    val address: String,
    val city: String,
    val zipcode: String,
    val phone_number: String
) : DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.email, this.city, this.zipcode, this.address, this.phone_number);
    }
}
