package gift;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public ProductService() {
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public void addProduct(Product product) {
        productRepository.addProduct(new Product(product.getName(), product.getImageUrl(), product.getPrice()));
    }

    public void deleteProduct(String name) {
        productRepository.deleteProduct(name);
    }

    public void updateProduct(String name, Product product) {
        productRepository.updateProduct(name, product);
    }

    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

}
