package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.Order
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.services.OrderService
import no.ntnu.webdev.webproject7.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrderController(
    private val orderService: OrderService,
    private val userService: UserService,
) : CrudController<Order, OrderId>(orderService) {

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun getUsersOrders(@PathVariable id: UserEntityId): ResponseEntity<List<Order>> {
        val user: User? = this.userService.getSessionUser();
        if (user == null || user.id != id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity(this.orderService.getUsersOrders(id), HttpStatus.OK)
    }
}
