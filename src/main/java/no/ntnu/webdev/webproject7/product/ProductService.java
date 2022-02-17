package no.ntnu.webdev.webproject7.product;

import no.ntnu.webdev.webproject7.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productRepository.save(new Product("0", 12.99, 0.5, null, "", "", ""));
    }

    public List<Product> getAllProducts() {
        return Utilities.iterableToList(this.productRepository.findAll());
    }

    public Product getProductById(String id) {
        // Guard condition
        if (id == null) {
            return null;
        }
        Optional<Product> result = this.productRepository.findById(id);
        return result.orElse(null);
    }

    public boolean addProduct(Product product) {
        if (product == null) {
            return false;
        }
        return this.productRepository.save(product).equals(product);
    }

    public boolean updateProduct(Product product) {
        if (product == null) {
            return false;
        }
        this.productRepository.save(product);
        return true;
    }

    public boolean deleteProduct(String id) {
        if (id == null) {
            return false;
        }
        this.productRepository.deleteById(id);
        return this.getProductById(id) == null;
    }
}
