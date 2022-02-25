package no.ntnu.webdev.webproject7.user

import no.ntnu.webdev.webproject7.crud.CrudService
import org.springframework.stereotype.Service

@Service
class UserService(userRepository: UserRepository) : CrudService<UserEntity, String>(userRepository)