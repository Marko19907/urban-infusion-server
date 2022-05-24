package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.UserImage
import no.ntnu.webdev.webproject7.models.UserImageId
import no.ntnu.webdev.webproject7.repositories.UserImageRepository
import org.springframework.stereotype.Service

@Service
class UserImageService(
    userImageRepository: UserImageRepository
) : ImageService<UserImage, UserImageId>(userImageRepository) {

    override fun createEntity(id: UserImageId): UserImage {
        return UserImage(id);
    }
}
