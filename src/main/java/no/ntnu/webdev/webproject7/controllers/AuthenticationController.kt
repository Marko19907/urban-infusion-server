package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.AuthenticationRequest
import no.ntnu.webdev.webproject7.models.AuthenticationResponse
import no.ntnu.webdev.webproject7.services.AppUserDetailsService
import no.ntnu.webdev.webproject7.utilities.JWTUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    @Autowired private val authenticationManager: AuthenticationManager,
    @Autowired private val userDetailsService: AppUserDetailsService,
    @Autowired private val jwtUtils: JWTUtils
) {

    @PostMapping("/authenticate")
    fun createAuthenticationToken(
        @RequestBody authenticationRequest: AuthenticationRequest
    ): ResponseEntity<AuthenticationResponse> {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    authenticationRequest.username,
                    authenticationRequest.password
                )
            )
        } catch (e: BadCredentialsException) {
            throw Exception("Incorrect username or password: ", e)
        }

        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)

        val jwt = jwtUtils.generateToken(userDetails)

        return ResponseEntity.ok(AuthenticationResponse(jwt))
    }
}