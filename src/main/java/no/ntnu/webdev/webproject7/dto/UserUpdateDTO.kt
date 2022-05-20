package no.ntnu.webdev.webproject7.dto

class UserUpdateDTO(
    val email: String?,
    val address: String?,
    val city: String?,
    val zipcode: String?,
    val phone_number: String?
) : DTO {

    override fun validate(): Boolean {
        return true;
    }
}
