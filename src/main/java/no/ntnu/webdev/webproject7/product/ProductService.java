package no.ntnu.webdev.webproject7.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public boolean addProduct(Product product) {
        if (this.products.contains(product)) {
            return false;
        }
        return this.products.add(product);
    }

    public List<Product> getAllProducts() {
        return this.products;
    }

    public boolean updateProduct(Product product) {
        if (!this.products.contains(product)) {
            return false;
        }
        this.products.set(this.products.indexOf(product), product);
        return true;
    }

    public boolean deleteProduct(String id) {
        return this.products.removeIf(product -> Objects.equals(product.getId(), id));
    }
}
