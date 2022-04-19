package no.ntnu.webdev.webproject7.dto

import no.ntnu.webdev.webproject7.utilities.objectsNotNull

data class RegistrationDTO(
    val username: String,
    val email: String,
    val password: String
) : DTO {

    override fun validate(): Boolean {
        val fields = listOf(username, email, password);
        return objectsNotNull(fields) && fields.none { s: String -> s.isBlank() };
    }
};