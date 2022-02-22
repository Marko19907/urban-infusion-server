package no.ntnu.webdev.webproject7.user

import no.ntnu.webdev.webproject7.utilities.iterableToList
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    val allUsers: List<UserEntity>
        get() = iterableToList(userRepository.findAll());

    private fun getUserById(id: String?): UserEntity? {
        if (id == null) {
            return null
        }
        return userRepository.findById(id).orElse(null)
    }

    fun addUser(userEntity: UserEntity?): Boolean {
        if (userEntity == null || getUserById(userEntity.id) != null) {
            return false
        }
        return userRepository.save(userEntity).id == userEntity.id
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