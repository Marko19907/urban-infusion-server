package no.ntnu.webdev.webproject7.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {
    @get:GetMapping("")
    val all: ResponseEntity<List<UserEntity>>
        get() = ResponseEntity(userService.allUsers, HttpStatus.OK)

    @PostMapping("")
    fun addOne(@RequestBody userEntity: UserEntity?): ResponseEntity<String> {
        return if (userService.addUser(userEntity)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PutMapping("")
    fun update(@RequestBody userEntity: UserEntity?): ResponseEntity<String> {
        return if (userService.updateUser(userEntity)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String?): ResponseEntity<String> {
        return if (userService.deleteUser(id)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}