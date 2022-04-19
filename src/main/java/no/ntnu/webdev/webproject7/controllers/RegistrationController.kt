package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.dto.RegistrationDTO
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
        return if (this.userService.createUser(registrationDTO)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}
