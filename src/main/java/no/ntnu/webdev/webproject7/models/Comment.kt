package no.ntnu.webdev.webproject7.models

import no.ntnu.webdev.webproject7.utilities.objectsNotNull
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
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

const val MAX_COMMENT_LENGTH = 1000;

@Entity
@Table(name = "comment")
open class Comment(
    @NotNull
    @OneToOne
    open var user: User? = null,

    @Column(nullable = false, length = MAX_COMMENT_LENGTH)
    open var text: String? = null,

) : CrudModel<CommentId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: CommentId = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    open var created: LocalDateTime = LocalDateTime.now();

    @Column(nullable = true, updatable = true)
    open var lastUpdated: LocalDateTime? = null;

    @PreUpdate
    protected open fun onUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }

    override fun validate(): Boolean {
        return objectsNotNull(this.text); // TODO: The date is not being checked for null!
    }
}
