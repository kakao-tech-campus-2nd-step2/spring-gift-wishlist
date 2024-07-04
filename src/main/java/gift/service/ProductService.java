package gift.service;
import gift.exception.InvalidProductException;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;
import gift.model.Product;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;



@Service
@RequestMapping
public class ProductService  {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id) {
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Product not found: " + e.getMessage());
        }
    }



    public Product addProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }


    public void updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }

        product.setId(id);
        validateProduct(product);
        productRepository.update(product);
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
