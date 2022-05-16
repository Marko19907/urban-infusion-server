package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.RegistrationDTO
import no.ntnu.webdev.webproject7.exceptions.UserRegistrationFailedException
import no.ntnu.webdev.webproject7.models.Role
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.repositories.UserRepository
import no.ntnu.webdev.webproject7.utilities.checkPasswordLength
import no.ntnu.webdev.webproject7.utilities.hashPassword
import no.ntnu.webdev.webproject7.utilities.validateEmail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserService(
    @Autowired private val userRepository: UserRepository
) : CrudService<User, UserEntityId>(userRepository) {

    @Throws(UserRegistrationFailedException::class)
    fun createUser(registrationDTO: RegistrationDTO): Boolean {
        if (!registrationDTO.validate()) {
            throw UserRegistrationFailedException("The request is incorrectly formatted!");
        }
        if (!checkPasswordLength(registrationDTO.password)) {
            throw UserRegistrationFailedException("The given password is too short or too long!");
        }
        if (!validateEmail(registrationDTO.email)) {
            throw UserRegistrationFailedException("The given email address is incorrectly formatted!");
        }
        if (this.userRepository.findOneByUsername(registrationDTO.username) != null) {
            throw UserRegistrationFailedException("A user with the given username already exists!");
        }
        val user = this.createUserEntity(registrationDTO);
        return if (this.add(user)) true else throw UserRegistrationFailedException("Failed to add the new user to the database!");
    }

    private fun createUserEntity(registrationDTO: RegistrationDTO): User {
        return User(
            registrationDTO.email,
            registrationDTO.username,
            hashPassword(registrationDTO.password),
            "",
            "",
            "",
            "",
            Role.USER
        );
    }

    fun getSessionUser(): User? {
        val securityContext = SecurityContextHolder.getContext();
        val authentication = securityContext.authentication;
        val username = authentication.name;
        return this.userRepository.findOneByUsername(username);
    }
}
