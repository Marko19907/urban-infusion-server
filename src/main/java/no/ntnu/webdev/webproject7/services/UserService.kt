package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.RegistrationDTO
import no.ntnu.webdev.webproject7.models.Role
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

private const val MIN_PASSWORD_LENGTH = 8;
private const val MAX_PASSWORD_LENGTH = 20;

@Service
class UserService(@Autowired private val userRepository: UserRepository) :
    CrudService<User, UserEntityId>(userRepository) {

    fun createUser(registrationDTO: RegistrationDTO): Boolean {
        if (!registrationDTO.validate()) {
            return false;
        }
        // TODO: Check if the username exists?
        if (!checkPasswordLength(registrationDTO.password)) {
            return false;
        }
        val user = createUserEntity(registrationDTO);
        return add(user);
    }

    private fun createUserEntity(registrationDTO: RegistrationDTO): User {
        return User(
            registrationDTO.email,
            registrationDTO.username,
            encodePassword(registrationDTO.password),
            "",
            "",
            "",
            "",
            Role.USER
        );
    }

    private fun checkPasswordLength(password: String): Boolean {
        if (password.isBlank()) {
            return false;
        }
        if (password.length < MIN_PASSWORD_LENGTH || password.length > MAX_PASSWORD_LENGTH) {
            return false;
        }
        return true;
    }

    private fun encodePassword(password: String): String {
        return BCryptPasswordEncoder().encode(password);
    }
}
