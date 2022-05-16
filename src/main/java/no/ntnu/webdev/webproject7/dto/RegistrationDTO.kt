package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.utilities.objectsNotNull

data class RegistrationDTO(
    val username: String,
    val email: String,
    val password: String
) : DTO {

    override fun validate(): Boolean {
        val fields = listOf(this.username, this.email, this.password);
        return objectsNotNull(fields, this.username, this.email, this.password)
                && fields.none { s: String -> s.isBlank() };
    }
};
