package gift.application;

import gift.domain.Product;
import gift.infra.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public void addProduct(Product product) {
        productRepository.addProduct(new Product(product.getName(), product.getPrice(), product.getImageUrl()));
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
