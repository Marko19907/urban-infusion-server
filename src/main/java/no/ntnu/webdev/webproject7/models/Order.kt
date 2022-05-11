package no.ntnu.webdev.webproject7.models

import com.fasterxml.jackson.annotation.JsonProperty
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

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
    @JsonProperty("products")
    @OneToMany(cascade = [CascadeType.MERGE])
    open val ordersProducts: List<OrdersProducts>? = null,

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    open var status: OrderStatus = OrderStatus.IDLE,

    @Column(nullable = true)
    open var totalPrice: Float? = null,

    @OneToOne
    open var user: User? = null,

    ): CrudModel<OrderId> {
    @Id
    @JsonProperty("orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: OrderId = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    var date: LocalDate = LocalDate.now();

    protected constructor() : this(null)

    override fun validate(): Boolean {
        return objectsNotNull(this.id, this.status, this.ordersProducts, this.user, this.date, this.totalPrice);
    }
}
