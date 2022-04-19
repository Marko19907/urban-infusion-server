package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.RegistrationDTO
import no.ntnu.webdev.webproject7.models.Role
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.repositories.UserRepository
import no.ntnu.webdev.webproject7.utilities.checkPasswordLength
import no.ntnu.webdev.webproject7.utilities.hashPassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) :
    CrudService<User, UserEntityId>(userRepository) {

    fun createUser(registrationDTO: RegistrationDTO): Boolean {
        if (!registrationDTO.validate()) {
            return false;
        }
        if (!checkPasswordLength(registrationDTO.password)) {
            return false;
        }
        if (this.userRepository.findOneByUsername(registrationDTO.username) != null) {
            return false;
        }
        val user = this.createUserEntity(registrationDTO);
        return this.add(user);
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
}
