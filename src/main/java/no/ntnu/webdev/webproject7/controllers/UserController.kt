package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.UserEntity
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.services.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(userService: UserService) : CrudController<UserEntity, UserEntityId>(userService)
