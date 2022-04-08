package no.ntnu.webdev.webproject7.filters

import no.ntnu.webdev.webproject7.services.AppUserDetailsService
import no.ntnu.webdev.webproject7.utilities.JWTUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JWTRequestFilter(
    @Autowired private val userDetailsService: AppUserDetailsService,
    @Autowired private val jwtUtils: JWTUtils
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        var username: String? = null
        var jwt: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7)
            username = jwtUtils.extractUsername(jwt)
        }

        if (username != null && jwt != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(username)
            if (jwtUtils.validateToken(jwt, userDetails)) {
                val token = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                token.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = token
            }
        }
        filterChain.doFilter(request, response)
    }
}