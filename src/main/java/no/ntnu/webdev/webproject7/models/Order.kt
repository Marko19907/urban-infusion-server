package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.CreationTimestamp
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
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.IDLE,

    @Column(nullable = true)
    var totalPrice: Float? = null,

    ): CrudModel<OrderId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: OrderId = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    var date: LocalDate = LocalDate.now();

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(this.id, this.status, this.products, this.date, this.totalPrice);
    }
}
