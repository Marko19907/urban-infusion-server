package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.dto.RegistrationDTO
import no.ntnu.webdev.webproject7.exceptions.UserRegistrationFailedException
import no.ntnu.webdev.webproject7.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("register")
class RegistrationController(private val userService: UserService) {

    @PostMapping("")
    fun registerNewUser(@RequestBody registrationDTO: RegistrationDTO): ResponseEntity<String> {
        try {
            this.userService.createUser(registrationDTO);
        }
        catch (e: UserRegistrationFailedException) {
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity(HttpStatus.OK);
    }
}
