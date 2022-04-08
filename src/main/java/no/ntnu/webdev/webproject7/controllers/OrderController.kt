package no.ntnu.webdev.webproject7.controllers

import no.ntnu.webdev.webproject7.models.OrderEntity
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.services.OrderService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrderController(orderService: OrderService) : CrudController<OrderEntity, OrderId>(orderService)