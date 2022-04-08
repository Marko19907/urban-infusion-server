package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.OrderEntity
import no.ntnu.webdev.webproject7.models.OrderId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<OrderEntity, OrderId>
