package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.add(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id);
    }

    public void updateProduct(Long id, Product product) {
        productRepository.update(id, product);
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}