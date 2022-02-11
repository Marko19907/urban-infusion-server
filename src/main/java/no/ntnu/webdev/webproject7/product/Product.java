package no.ntnu.webdev.webproject7.product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private String id;
    private Double price;
    private Double discount;
    private String image;
    private String title;
    private String description;
    private String weight;

    public Product(String id, Double price, Double discount, String image, String title, String description, String weight) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.title = title;
        this.description = description;
        this.weight = weight;
    }

    protected Product() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}


