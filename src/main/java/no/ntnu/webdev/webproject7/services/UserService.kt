package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.UserEntity
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired userRepository: UserRepository) : CrudService<UserEntity, UserEntityId>(userRepository)
