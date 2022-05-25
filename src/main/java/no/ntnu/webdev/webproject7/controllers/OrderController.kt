package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.dto.OrderDTO
import no.ntnu.webdev.webproject7.dto.OrderUpdateDTO
import no.ntnu.webdev.webproject7.models.Order
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.services.OrderService
import no.ntnu.webdev.webproject7.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrderController(
    private val orderService: OrderService,
    private val userService: UserService,
) {

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun all(): ResponseEntity<List<Order>> {
        return ResponseEntity(this.orderService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: OrderId): ResponseEntity<Order> {
        val entity = this.orderService.getById(id);
        return if (entity != null) ResponseEntity(entity, HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    fun addOne(@RequestBody entity: OrderDTO): ResponseEntity<String> {
        val user: User? = this.userService.getSessionUser();
        if (user == null || user.id != entity.userId) {
            return ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return if (this.orderService.add(entity, user)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun update(@RequestBody entity: OrderUpdateDTO): ResponseEntity<String> {
        return if (this.orderService.update(entity)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun delete(@PathVariable id: OrderId): ResponseEntity<String> {
        return if (this.orderService.delete(id)) ResponseEntity(HttpStatus.OK) else ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun getUsersOrders(@PathVariable id: UserEntityId): ResponseEntity<List<Order>> {
        val user: User? = this.userService.getSessionUser();
        if (user == null || user.id != id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity(this.orderService.getUsersOrders(id), HttpStatus.OK);
    }
}
