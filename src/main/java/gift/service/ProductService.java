package gift.service;

import gift.repository.ProductRepository;
import gift.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Product getProductById(long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void saveProduct(Product product) {
        if (product.getId() == 0) {
            productRepository.save(product);
        } else {
            productRepository.update(product);
        }
    }

    @Transactional
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
}
