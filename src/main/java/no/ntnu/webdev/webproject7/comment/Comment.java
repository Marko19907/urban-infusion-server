package no.ntnu.webdev.webproject7.comment;

import no.ntnu.webdev.webproject7.crud.CrudModel;
import no.ntnu.webdev.webproject7.product.Product;
import no.ntnu.webdev.webproject7.user.UserEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Comment implements CrudModel<String> {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String text;
    private LocalDate date;

    public Comment(String id, Product product, UserEntity user, String text, LocalDate date) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.text = text;
        this.date = date;
    }

    protected Comment() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
