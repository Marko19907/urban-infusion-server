package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.OrderUpdateDTO
import no.ntnu.webdev.webproject7.models.Order
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    @Autowired private val orderRepository: OrderRepository
) : CrudService<Order, OrderId>(orderRepository) {

    fun update(orderUpdateDTO: OrderUpdateDTO?): Boolean {
        if (orderUpdateDTO == null || !orderUpdateDTO.validate()) {
            return false;
        }
        val order = this.orderRepository.findByIdOrNull(orderUpdateDTO.orderId) ?: return false;
        order.status = orderUpdateDTO.status;
        return this.update(order);
    }

    fun getUsersOrders(userId: UserEntityId): List<Order> {
        return this.orderRepository.findOrdersByUser_Id(userId);
    }
}
