package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.UserImage
import no.ntnu.webdev.webproject7.models.UserImageId
import no.ntnu.webdev.webproject7.services.ImageService
import no.ntnu.webdev.webproject7.services.UserImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user-images")
class UserImageController(
    @Autowired private val userImageService: UserImageService
) : ImageController<UserImage, UserImageId>() {

    override val service: ImageService<UserImage, UserImageId> = this.userImageService;

}
