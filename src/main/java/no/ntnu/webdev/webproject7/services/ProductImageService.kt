package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.ProductImage
import no.ntnu.webdev.webproject7.models.ProductImageId
import no.ntnu.webdev.webproject7.repositories.ProductImageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * Types of content which are considered images
 */
private val IMAGE_CONTENT_TYPES = hashSetOf("image/jpg", "image/png", "image/jpeg", "image/webp");

@Service
class ProductImageService(
    private val productImageRepository: ProductImageRepository
) : CrudService<ProductImage, ProductImageId>(productImageRepository) {

    fun add(id: ProductImageId, imageData: MultipartFile?): Boolean {
        if (imageData == null || imageData.isEmpty || !this.isImage(imageData)) {
            return false;
        }

        val productImage = ProductImage(id, null, this.getFileExtension(imageData));
        productImage.image = imageData.bytes;

        this.productImageRepository.save(productImage);
        return this.productImageRepository.findByIdOrNull(id)?.id == id;
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
