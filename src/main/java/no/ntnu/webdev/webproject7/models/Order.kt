package no.ntnu.webdev.webproject7.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import no.ntnu.webdev.webproject7.serializers.StatusEnumDeserializer
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.PrePersist
import javax.persistence.Table
import javax.validation.constraints.Positive

typealias OrderId = Int;

@JsonDeserialize(using = StatusEnumDeserializer::class)
enum class OrderStatus(val status: Int) {
    RECEIVED(0),    // In shopping cart
    PROCESSING(1),  // Ordered, waiting for processing
    SENT(2),        // Product sent
    DELIVERED(3),   // Product delivered
    CANCELED(4),    // Order cancelled
}

@Entity
@Table(name = "OrderEntity")
open class Order(

    @JoinColumn
    @JsonProperty("products")
    @OneToMany(cascade = [CascadeType.REMOVE])
    open val ordersProducts: List<OrdersProducts>? = null,

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    open var status: OrderStatus = OrderStatus.RECEIVED,

    @OneToOne(orphanRemoval = false)
    open var user: User? = null,

) : CrudModel<OrderId> {

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
                .map { it.getPrice() }
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
        }
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.id, this.status, this.ordersProducts, this.user, this.date, this.totalPrice);
    }
}
