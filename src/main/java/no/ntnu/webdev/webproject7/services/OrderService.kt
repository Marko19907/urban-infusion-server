package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.Order
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(
    @Autowired private val orderRepository: OrderRepository
) : CrudService<Order, OrderId>(orderRepository) {

    fun getUsersOrders(userId: UserEntityId): List<Order> {
        return this.orderRepository.findOrdersByUser_Id(userId);
    }
}
