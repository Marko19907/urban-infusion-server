package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.ImageModel
import no.ntnu.webdev.webproject7.services.ImageService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.request.WebRequest
import org.springframework.web.multipart.MultipartFile
import java.time.ZoneId
import java.time.format.DateTimeFormatter

abstract class ImageController<EntityType : ImageModel<ID>, ID> {

    abstract val service: ImageService<EntityType, ID>;

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: ID, request: WebRequest): ResponseEntity<ByteArray> {
        val image: EntityType = this.service.getById(id) ?: return ResponseEntity(HttpStatus.BAD_REQUEST);

        val zoneId = ZoneId.systemDefault();
        val lastModified = image.lastModified.atZone(zoneId);
        val lastModifiedTimestamp = lastModified.toInstant().toEpochMilli();

        if (request.checkNotModified(lastModifiedTimestamp)) {
            return ResponseEntity(HttpStatus.NOT_MODIFIED);
        }

        return ResponseEntity.ok()
            .header(
                "Last-Modified",
                DateTimeFormatter.RFC_1123_DATE_TIME.format(lastModified)
            )
            .header("Cache-Control", "public, max-age=0, must-revalidate")
            .header(HttpHeaders.CONTENT_TYPE, "image/${image.extension}")
            .body(image.image);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    open fun upload(
        @PathVariable id: ID,
        @RequestParam("fileContent") multipartFile: MultipartFile?
    ): ResponseEntity<String> {
        return if (this.service.add(
                id,
                multipartFile
            )
        ) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
