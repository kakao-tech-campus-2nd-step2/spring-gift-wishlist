package gift.service;
import gift.Product;
import gift.controller.PageController;
import gift.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private long currentId = 1;

    @Autowired
    ProductRepository productRepository;

    // 모든 제품 조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 특정 제품 조회
    public Product getProductById(Long id) {
        Product product = productRepository.find(id);
        if (product == null) {
            throw new NoSuchElementException("Product not found with id " + id);
        }
        return product;
    }

    // 제품 추가
    public void addProduct(Product product) {
        if (product.getId() == null) {
            product.setId(currentId++);
        }
        productRepository.insert(product);
    }

    // 제품 수정
    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    // 제품 삭제
    public void deleteProduct(Long id) {
        productRepository.remove(id);
    }
}