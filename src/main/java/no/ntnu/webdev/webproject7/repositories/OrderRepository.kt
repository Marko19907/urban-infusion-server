package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.Order
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.UserEntityId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, OrderId> {

    fun findOrdersByUser_Id(userId: UserEntityId): List<Order>;
}
