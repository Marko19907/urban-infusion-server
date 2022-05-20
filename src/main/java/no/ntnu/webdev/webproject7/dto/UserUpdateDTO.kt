package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.utilities.objectsNotNull

class UserUpdateDTO(
    email: String,
    address: String,
    city: String,
    zipcode: String,
    phone_number: String
) : UserUpdatePartialDTO(email, address, city, zipcode, phone_number), DTO {

    override fun validate(): Boolean {
        return objectsNotNull(this.email, this.address, this.city, this.zipcode, this.phone_number);
    }
}
