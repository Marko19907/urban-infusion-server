package no.ntnu.webdev.webproject7.comment;
import java.time.LocalDate;

public class Comment {

    private int id;
    private int productId;
    private int userId;
    private String text;
    private LocalDate date;

    public Comment(int id, int productId, int userId, String text, LocalDate date) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.text = text;
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

