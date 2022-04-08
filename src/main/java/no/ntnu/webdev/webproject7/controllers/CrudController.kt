package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.CrudModel
import no.ntnu.webdev.webproject7.services.CrudService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

open class CrudController<EntityType : CrudModel<ID>, ID>(
    private val service: CrudService<EntityType, ID>
) {
    @GetMapping("")
    fun all(): ResponseEntity<List<EntityType>> {
        return ResponseEntity(service.all(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: ID): ResponseEntity<EntityType> {
        val entity = service.getById(id);
        return if (entity != null) ResponseEntity(entity, HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("")
    fun addOne(@RequestBody entity: EntityType): ResponseEntity<String> {
        return if (service.add(entity)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @PutMapping("")
    fun update(@RequestBody entity: EntityType): ResponseEntity<String> {
        return if (service.update(entity)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID): ResponseEntity<String> {
        return if (service.delete(id)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}