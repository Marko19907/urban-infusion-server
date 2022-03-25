package no.ntnu.webdev.webproject7.order

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, OrderId>
