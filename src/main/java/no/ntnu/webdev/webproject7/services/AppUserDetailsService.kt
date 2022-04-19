package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class AppUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(s: String): UserDetails {
        val user = this.userRepository.findOneByUsername(s) ?: throw UsernameNotFoundException("Username not found!");

        val authorities = ArrayList<GrantedAuthority>();
        authorities.add(SimpleGrantedAuthority(user.role.toString()));
        return User(
            user.username,
            user.password,
            authorities
        );
    }
}
