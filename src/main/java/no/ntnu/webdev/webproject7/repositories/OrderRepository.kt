package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.Order
import no.ntnu.webdev.webproject7.models.OrderId
import no.ntnu.webdev.webproject7.models.UserEntityId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, OrderId> {

    @Query("SELECT o FROM Order o WHERE o.user.id = ?1")
    fun getUsersOrders(userId: UserEntityId): List<Order>;
}
