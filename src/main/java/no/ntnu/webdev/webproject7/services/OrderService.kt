package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.OrderDTO
import no.ntnu.webdev.webproject7.dto.OrderUpdateDTO
import no.ntnu.webdev.webproject7.models.Order
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.OrderStatus
import no.ntnu.webdev.webproject7.models.OrdersProducts
import no.ntnu.webdev.webproject7.models.User
import no.ntnu.webdev.webproject7.models.UserEntityId
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import no.ntnu.webdev.webproject7.repositories.OrdersProductsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    @Autowired private val orderRepository: OrderRepository,
    @Autowired private val productService: ProductService,
    @Autowired private val ordersProductsRepository: OrdersProductsRepository
) : CrudService<Order, OrderId>(orderRepository) {

    fun add(orderDTO: OrderDTO?, user: User): Boolean {
        if (orderDTO == null || !orderDTO.validate()) {
            return false;
        }
        if (!this.verifyOrderDTOProducts(orderDTO)) {
            return false;
        }

        val ordersProducts = orderDTO.products
            .map { OrdersProducts(this.productService.getById(it.productId), it.quantity) }
            .toList();
        val order = Order(ordersProducts, OrderStatus.PROCESSING, user);

        ordersProducts.forEach { this.ordersProductsRepository.save(it) };
        return super.add(order);
    }

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

    /**
     * Returns true if the Products referred to by the OrderDTO exist.
     */
    private fun verifyOrderDTOProducts(orderDTO: OrderDTO): Boolean {
        return !orderDTO.products
            .map { it.productId }
            .map { this.productService.getById(it) }
            .any { it == null }
    }
}
