package no.ntnu.webdev.webproject7.comment;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Comment {

    @Id
    private String id;
    private String productId;
    private String userId;
    private String text;
    private LocalDate date;

    public Comment(String id, String productId, String userId, String text, LocalDate date) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.text = text;
        this.date = date;
    }

    protected Comment() {}

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

