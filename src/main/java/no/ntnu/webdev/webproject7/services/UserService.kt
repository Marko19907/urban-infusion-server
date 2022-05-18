package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.RegistrationDTO
import no.ntnu.webdev.webproject7.dto.UserPasswordUpdateDTO
import no.ntnu.webdev.webproject7.dto.UserUpdateDTO
import no.ntnu.webdev.webproject7.exceptions.UserRegistrationFailedException
import no.ntnu.webdev.webproject7.exceptions.UserUpdateFailedException
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
        when {
            !registrationDTO.validate() -> {
                throw UserRegistrationFailedException("The request is incorrectly formatted!");
            }
            !checkPasswordLength(registrationDTO.password) -> {
                throw UserRegistrationFailedException("The given password is too short or too long!");
            }
            !validateEmail(registrationDTO.email) -> {
                throw UserRegistrationFailedException("The given email address is incorrectly formatted!");
            }
            this.userRepository.findOneByUsername(registrationDTO.username) != null -> {
                throw UserRegistrationFailedException("A user with the given username already exists!");
            }
            else -> {
                val user = this.createUserEntity(registrationDTO);
                return if (this.add(user)) true else throw UserRegistrationFailedException("Failed to add the new user to the database!");
            }
        }
    }

    @Throws(UserUpdateFailedException::class)
    fun update(userUpdateDTO: UserUpdateDTO, user: User): Boolean {
        when {
            !userUpdateDTO.validate() -> {
                throw UserUpdateFailedException("The request is incorrectly formatted!");
            }
            userUpdateDTO.email.isBlank() -> {
                throw UserUpdateFailedException("The new email can not be blank!");
            }
            !validateEmail(userUpdateDTO.email) -> {
                throw UserUpdateFailedException("The new email must be a valid email!");
            }
            else -> {
                user.email = userUpdateDTO.email;
                user.address = userUpdateDTO.address;
                user.city = userUpdateDTO.city;
                user.zipcode = userUpdateDTO.zipcode;
                user.phone_number = userUpdateDTO.phone_number;

                return this.update(user);
            }
        }
    }

    @Throws(UserUpdateFailedException::class)
    fun updatePassword(userPasswordUpdateDTO: UserPasswordUpdateDTO, user: User): Boolean {
        when {
            !userPasswordUpdateDTO.validate() -> {
                throw UserUpdateFailedException("The request is incorrectly formatted!");
            }
            userPasswordUpdateDTO.password.isBlank() -> {
                throw UserUpdateFailedException("The new password can not be blank!");
            }
            !checkPasswordLength(userPasswordUpdateDTO.password) -> {
                throw UserUpdateFailedException("The new password is too short or too long!");
            }
            else -> {
                user.password = hashPassword(userPasswordUpdateDTO.password);

                return this.update(user);
            }
        }
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
