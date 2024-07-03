package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedProduct.setId(id);
        productRepository.update(updatedProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{productId}/options/{optionId}")
    public ResponseEntity<Option> updateOption(@PathVariable Long productId, @PathVariable Long optionId, @RequestBody Option updatedOption) {
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

    @DeleteMapping("/{productId}/options/{optionId}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long productId, @PathVariable Long optionId) {
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
