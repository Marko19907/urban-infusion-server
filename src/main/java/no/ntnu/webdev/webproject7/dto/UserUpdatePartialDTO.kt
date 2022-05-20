package no.ntnu.webdev.webproject7.dto

open class UserUpdatePartialDTO(
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
