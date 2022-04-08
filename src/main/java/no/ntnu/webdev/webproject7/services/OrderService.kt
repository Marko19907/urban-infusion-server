package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.OrderEntity
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired orderRepository: OrderRepository) : CrudService<OrderEntity, OrderId>(orderRepository)