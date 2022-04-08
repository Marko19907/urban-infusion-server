package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService(
    @Autowired private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findUserEntityByUsername(username)
        return User(
            user.username,
            user.password,
            mutableListOf()
        )
    }
}