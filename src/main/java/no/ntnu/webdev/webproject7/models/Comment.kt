package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.PreUpdate
import javax.persistence.Table
import javax.validation.constraints.NotNull

typealias CommentId = Long;

@Entity
@Table(name = "comment")
open class Comment(
    @NotNull
    @OneToOne
    open var user: User? = null,

    @Column(nullable = false, length = 1000)
    open var text: String? = null,

    @Column(nullable = true, updatable = true)
    open var lastUpdated: LocalDate? = null,

) : CrudModel<CommentId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: CommentId = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    open var created: LocalDate = LocalDate.now();

    protected constructor() : this(null);

    @PreUpdate
    protected open fun onUpdate() {
        this.lastUpdated = LocalDate.now();
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.text); // TODO: The date is not being checked for null!
    }
}
