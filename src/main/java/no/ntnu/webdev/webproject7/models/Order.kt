package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import java.time.LocalDate
import javax.persistence.*

typealias OrderId = Int;

enum class OrderStatus(val status: Int) {
    IDLE(0),        // In shopping cart
    PROCESSING(1),  // Ordered, waiting for processing
    SENT(2),        // Product sent
    DELIVERED(3),   // Product delivered
}

@Entity
@Table(name = "OrderEntity")
open class Order(
    @Column(nullable = true)
    @OneToMany(cascade = [CascadeType.MERGE])
    val products: List<Product>? = null,

    @Column(nullable = true)
    var status: OrderStatus = OrderStatus.IDLE,

    @Column(nullable = true)
    var totalPrice: Float? = null,

    ): CrudModel<OrderId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: OrderId = 0

    @Column(nullable = true)
    var date: LocalDate = LocalDate.now();

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(id, status, products, date, totalPrice);
    }
}