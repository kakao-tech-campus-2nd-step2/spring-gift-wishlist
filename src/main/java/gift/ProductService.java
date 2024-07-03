package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public void update(Product updatedProduct) {
        productRepository.update(updatedProduct);
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }

    public ResponseEntity<Option> updateOption(Long productId, Long optionId, Option updatedOption) {
        Product product = productRepository.findById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Option> options = product.getOptions();
        for (Option option : options) {
            if (option.getId().equals(optionId)) {
                option.setName(updatedOption.getName());
                option.setPrice(updatedOption.getPrice());
                productRepository.update(product);
                return new ResponseEntity<>(option, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> deleteOption(Long productId, Long optionId) {
        Product product = productRepository.findById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Option> options = product.getOptions();
        if (options.size() <= 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 최소 하나의 옵션이 남아있어야 함
        }

        options.removeIf(option -> option.getId().equals(optionId));
        productRepository.update(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
