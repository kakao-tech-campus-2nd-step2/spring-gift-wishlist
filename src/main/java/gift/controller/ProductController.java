package gift.controller;

import gift.model.Product;
import gift.repository.ProductRepository;
import jakarta.validation.Valid;
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

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> createProduct(@Valid @RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.update(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
