package no.ntnu.webdev.webproject7.order

import no.ntnu.webdev.webproject7.crud.CrudController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrderController(orderService: OrderService) : CrudController<OrderEntity, OrderId>(orderService)