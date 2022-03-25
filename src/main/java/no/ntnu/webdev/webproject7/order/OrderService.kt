package no.ntnu.webdev.webproject7.order

import no.ntnu.webdev.webproject7.crud.CrudService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired orderRepository: OrderRepository) : CrudService<OrderEntity, OrderId>(orderRepository)