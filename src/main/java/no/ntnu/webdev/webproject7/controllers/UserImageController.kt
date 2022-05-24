package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.UserImage
import no.ntnu.webdev.webproject7.models.UserImageId
import no.ntnu.webdev.webproject7.services.ImageService
import no.ntnu.webdev.webproject7.services.UserImageService
import no.ntnu.webdev.webproject7.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("user-images")
class UserImageController(
    @Autowired private val userImageService: UserImageService,
    @Autowired private val userService: UserService
) : ImageController<UserImage, UserImageId>() {

    override val service: ImageService<UserImage, UserImageId> = this.userImageService;

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    override fun upload(
        @PathVariable id: UserImageId,
        @RequestParam("fileContent") multipartFile: MultipartFile?
    ): ResponseEntity<String> {
        val user = this.userService.getSessionUser();
        if (user?.id != id.toLong()) {
            return ResponseEntity("You can only set your own profile picture!", HttpStatus.BAD_REQUEST);
        }
        return this.doUpload(id, multipartFile);
    }
}
