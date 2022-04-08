package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.UserEntity
import no.ntnu.webdev.webproject7.models.UserEntityId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserEntity, UserEntityId> {
    fun findUserEntityByUsername(username: String): UserEntity
}
