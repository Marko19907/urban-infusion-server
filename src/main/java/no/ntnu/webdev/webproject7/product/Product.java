package no.ntnu.webdev.webproject7.product;

import java.sql.Blob;

public class Product {
    private String id;
    private float price;
    private float discount;
    private Blob image;
    private String title;
    private String description;
    private String weight;

    public Product(String id, float price, float discount, Blob image, String title, String description, String weight) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.title = title;
        this.description = description;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
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
