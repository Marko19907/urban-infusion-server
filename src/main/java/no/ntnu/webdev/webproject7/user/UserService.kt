package no.ntnu.webdev.webproject7.user

import no.ntnu.webdev.webproject7.crud.CrudService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired userRepository: UserRepository) : CrudService<UserEntity, UserEntityId>(userRepository)
