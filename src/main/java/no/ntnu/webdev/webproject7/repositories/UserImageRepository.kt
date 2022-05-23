package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.UserImage
import no.ntnu.webdev.webproject7.models.UserImageId
import org.springframework.data.repository.CrudRepository

interface UserImageRepository : CrudRepository<UserImage, UserImageId>
