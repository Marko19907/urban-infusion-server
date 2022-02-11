package no.ntnu.webdev.webproject7.product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>(
                Arrays.asList(
                        new Product("0", 12.99, 0.5, null, "", "", ""),
                        new Product("1", 15.99, 0.3, null, "", "", ""),
                        new Product("2", 20.99, 0.2, null, "", "", "")
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
        if (!this.deleteProduct(product.getId())) {
            return false;
        }
        this.products.add(product);
        return true;
    }

    public boolean deleteProduct(String id) {
        return this.products.removeIf(product -> product.getId().equals(id));
    }
}
