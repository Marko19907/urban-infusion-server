package no.ntnu.webdev.webproject7.comment;
import java.time.LocalDate;

public class Comment {

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



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

