package no.ntnu.webdev.webproject7.user

import no.ntnu.webdev.webproject7.crud.CrudController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(userService: UserService) : CrudController<UserEntity, String>(userService)