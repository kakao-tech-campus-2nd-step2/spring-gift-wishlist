package gift.service;

import gift.exception.ProductAlreadyExistsException;
import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        checkForDuplicateProduct(product);
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        checkForDuplicateProduct(product);
        return productRepository.update(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void checkForDuplicateProduct(Product product) {
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            if (p.equalProduct(product)) {
                throw new ProductAlreadyExistsException(product.getName());
            }
        }
    }
}
