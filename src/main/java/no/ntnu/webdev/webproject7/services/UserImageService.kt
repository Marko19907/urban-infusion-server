package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.UserImage
import no.ntnu.webdev.webproject7.models.UserImageId
import no.ntnu.webdev.webproject7.repositories.UserImageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * Types of content which are considered images
 */
private val IMAGE_CONTENT_TYPES = hashSetOf("image/jpg", "image/png", "image/jpeg", "image/webp");

@Service
class UserImageService(
    private val userImageRepository: UserImageRepository
) : CrudService<UserImage, UserImageId>(userImageRepository) {

    fun add(id: UserImageId, imageData: MultipartFile?): Boolean {
        if (imageData == null || imageData.isEmpty || !this.isImage(imageData)) {
            return false;
        }

        val productImage = UserImage(id, null, this.getFileExtension(imageData));
        productImage.image = imageData.bytes;

        this.userImageRepository.save(productImage);
        return this.userImageRepository.findByIdOrNull(id)?.id == id;
    }


    /**
     * Check if the given file is an image.
     * @param file File to check
     * @return True if it looks like image, false if not
     */
    private fun isImage(file: MultipartFile?): Boolean {
        return file != null && this.isImageContentType(file.contentType);
    }

    /**
     * Checks if a given content-type of a file is an image-type.
     * @param contentType The content type to check
     * @return True if it is an image-type, false if it is not
     */
    private fun isImageContentType(contentType: String?): Boolean {
        if (contentType == null) {
            return false;
        }
        return IMAGE_CONTENT_TYPES.contains(contentType);
    }

    /**
     * Get extension of the file (.jpg, .png, ...).
     * @param imageData Image data as received from the web client
     * @return Image file extension
     */
    private fun getFileExtension(imageData: MultipartFile): String {
        if (imageData.contentType == null) {
            return "";
        }
        return imageData.contentType!!.split("/")[1];
    }
}

