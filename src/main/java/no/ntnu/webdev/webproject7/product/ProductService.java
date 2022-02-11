package no.ntnu.webdev.webproject7.product;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductService {
    private final List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>(
                Arrays.asList(
                        new Product("0", 12.99f, 0.5f, null, "", "", ""),
                        new Product("1", 15.99f, 0.3f, null, "", "", ""),
                        new Product("2", 20.99f, 0.2f, null, "", "", "")
                ));
    }

    public List<Product> getAllProducts() {
        return this.products;
    }
}
