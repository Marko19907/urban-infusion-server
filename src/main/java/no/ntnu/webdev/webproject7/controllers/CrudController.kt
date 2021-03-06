package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.CrudModel
import no.ntnu.webdev.webproject7.services.CrudService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

open class CrudController<EntityType : CrudModel<ID>, ID>(
    private val service: CrudService<EntityType, ID>
) {
    @GetMapping("")
    fun all(): ResponseEntity<List<EntityType>> {
        return ResponseEntity(this.service.all(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: ID): ResponseEntity<EntityType> {
        val entity = this.service.getById(id);
        return if (entity != null) ResponseEntity(entity, HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun addOne(@RequestBody entity: EntityType): ResponseEntity<String> {
        return if (this.service.add(entity)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun update(@RequestBody entity: EntityType): ResponseEntity<String> {
        return if (this.service.update(entity)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun delete(@PathVariable id: ID): ResponseEntity<String> {
        return if (this.service.delete(id)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}