package gift.controller;

import gift.model.Product;
import gift.repository.ProductRepository;
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

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        if (!isValidProduct(product)) {
            return new ResponseEntity<>("Invalid product data", HttpStatus.BAD_REQUEST);
        }
        Product newProduct = productRepository.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!isValidProduct(product)) {
            return new ResponseEntity<>("Invalid product data", HttpStatus.BAD_REQUEST);
        }
        Product updatedProduct = new Product(id, product.name(), product.price(), product.imageUrl());
        productRepository.update(updatedProduct);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean isValidProduct(Product product) {
        if (product.name() == null || product.name().isBlank()) {
            return false;
        }
        if (product.price() < 0) {
            return false;
        }
        return true;
    }
}