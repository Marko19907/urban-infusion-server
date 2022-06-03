package no.ntnu.webdev.webproject7.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.Positive

typealias OrderProductId = Int;

@Entity
@Table(name = "ordersProducts")
open class OrdersProducts(

    @ManyToOne
    @JsonIgnoreProperties("comments")
    open var product: Product? = null,

    @Positive
    open var quantity: Int = 0,

) : CrudModel<OrderProductId> {

    @Id
    @GeneratedValue
    override var id: OrderProductId = 0;

    /**
     * Returns the total price. (product * quantity)
     */
    @JsonIgnore
    fun getPrice(): BigDecimal {
        return BigDecimal(this.product?.price!!)
            .multiply(this.getDiscountFactor())
            .multiply(this.quantity.toBigDecimal());
    }

    /**
     * Calculates the discount percentage factor.
     */
    private fun getDiscountFactor(): BigDecimal {
        return when (this.product?.discount) {
            0.0 -> 1.toBigDecimal();
            else -> {
                return (1 - this.product!!.discount!!).toBigDecimal();
            }
        }
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.product) && this.quantity > 0;
    }
}
