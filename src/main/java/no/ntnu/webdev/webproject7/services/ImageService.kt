package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.exceptions.ImageUploadException
import no.ntnu.webdev.webproject7.models.ImageModel
import no.ntnu.webdev.webproject7.models.MAX_IMAGE_SIZE
import org.springframework.data.repository.CrudRepository
import org.springframework.web.multipart.MultipartFile

/**
 * Types of content which are considered images
 */
private val IMAGE_CONTENT_TYPES = hashSetOf("image/jpg", "image/png", "image/jpeg", "image/webp");

abstract class ImageService<EntityType : ImageModel<ID>, ID>(
    private val repository: CrudRepository<EntityType, ID>
) : CrudService<EntityType, ID>(repository) {

    @Throws(ImageUploadException::class)
    fun add(id: ID, imageData: MultipartFile?): Boolean {
        if (imageData == null || imageData.isEmpty) {
            throw ImageUploadException("The image can not be empty!");
        }
        if (!this.isImage(imageData)) {
            throw ImageUploadException("The file must be an image!");
        }
        if (imageData.bytes.size > MAX_IMAGE_SIZE) {
            throw ImageUploadException("The image is too large!");
        }

        val imageModel = this.createEntity(id);
        imageModel.extension = this.getFileExtension(imageData);
        imageModel.image = imageData.bytes;

        return this.addImage(imageModel);
    }

    /**
     * Creates the EntityType from an implementing class.
     */
    abstract fun createEntity(id: ID): EntityType;

    /**
     * Adds the given image entity to the DB.
     */
    private fun addImage(entity: EntityType): Boolean {
        if (!entity.validate()) {
            return false;
        }
        return this.repository.save(entity).id == entity.id;
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
