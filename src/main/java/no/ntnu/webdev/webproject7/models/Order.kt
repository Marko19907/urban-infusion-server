package no.ntnu.webdev.webproject7.models

import com.fasterxml.jackson.annotation.JsonProperty
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
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
import javax.persistence.PrePersist
import javax.persistence.Table
import javax.validation.constraints.Positive

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

    @OneToOne
    open var user: User? = null,

) : CrudModel<OrderId> {

    protected constructor() : this(null);

    @Id
    @JsonProperty("orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: OrderId = 0;

    @Positive
    @Column(nullable = false, updatable = false)
    open var totalPrice: BigDecimal = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    open var date: LocalDate = LocalDate.now();

    @PrePersist
    private fun setTotalPrice() {
        if (this.ordersProducts != null) {
            this.totalPrice = this.ordersProducts!!.stream()
                .map { orderProduct -> orderProduct.getPrice() }
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        }
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.id, this.status, this.ordersProducts, this.user, this.date, this.totalPrice);
    }
}
