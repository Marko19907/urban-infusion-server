package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.dto.UserPasswordUpdateDTO
import no.ntnu.webdev.webproject7.dto.UserUpdateDTO
import no.ntnu.webdev.webproject7.exceptions.UserUpdateFailedException
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(@Autowired private val userService: UserService) {

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun all(): ResponseEntity<List<User>> {
        return ResponseEntity(this.userService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getOne(@PathVariable id: UserEntityId): ResponseEntity<User> {
        val entity = this.userService.getById(id);
        return if (entity != null) ResponseEntity(entity, HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun changePassword(
        @PathVariable id: UserEntityId,
        @RequestBody userPasswordUpdateDTO: UserPasswordUpdateDTO
    ): ResponseEntity<String> {
        val user: User = this.userService.getSessionUser() ?: return ResponseEntity("Invalid authentication!", HttpStatus.BAD_REQUEST);
        if (user.id != id) {
            return ResponseEntity("The logged in user does not match the given user ID!", HttpStatus.BAD_REQUEST);
        }

        try {
            this.userService.updatePassword(userPasswordUpdateDTO, user);
        }
        catch (e: UserUpdateFailedException) {
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('USER')")
    fun update(@RequestBody userUpdateDTO: UserUpdateDTO): ResponseEntity<String> {
        val user: User = this.userService.getSessionUser() ?: return ResponseEntity("Invalid authentication!", HttpStatus.BAD_REQUEST);
        try {
            this.userService.update(userUpdateDTO, user);
        }
        catch (e: UserUpdateFailedException) {
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('USER')")
    fun getUser(): ResponseEntity<User> {
        val user = this.userService.getSessionUser();
        return if (user != null) ResponseEntity(user, HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
