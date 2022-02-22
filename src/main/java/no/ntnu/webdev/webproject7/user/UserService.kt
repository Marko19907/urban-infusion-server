package no.ntnu.webdev.webproject7.user

import no.ntnu.webdev.webproject7.utilities.iterableToList
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    val allUsers: List<UserEntity>
        get() = iterableToList(userRepository.findAll());

    private fun getUserById(id: String?): UserEntity? {
        // Guard condition
        if (id == null) {
            return null
        }
        val result = userRepository.findById(id)
        return result.orElse(null)
    }

    fun addUser(userEntity: UserEntity?): Boolean {
        if (userEntity == null) {
            return false
        }
        val saved = userRepository.save(userEntity)
        return userEntity.id == saved.id
    }

    fun updateUser(userEntity: UserEntity?): Boolean {
        if (userEntity == null) {
            return false
        }
        userRepository.save(userEntity)
        return true
    }

    fun deleteUser(id: String?): Boolean {
        if (id == null) {
            return false
        }
        userRepository.deleteById(id)
        return getUserById(id) == null
    }
}