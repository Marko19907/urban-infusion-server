package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.OrderProductId
import no.ntnu.webdev.webproject7.models.OrdersProducts
import org.springframework.data.repository.CrudRepository

interface OrdersProductsRepository : CrudRepository<OrdersProducts, OrderProductId>
