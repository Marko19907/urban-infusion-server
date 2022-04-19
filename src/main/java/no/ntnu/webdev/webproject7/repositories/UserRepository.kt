package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.models.UserEntityId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, UserEntityId> {
    fun findOneByUsername(username: String): User?
}
