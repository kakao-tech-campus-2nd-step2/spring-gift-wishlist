package gift.controller;

import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Pattern;

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
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        String productName = product.name();
        if (productName.length() > 15) {
            return new ResponseEntity<>("Name must be less than or equal to 15 characters", HttpStatus.BAD_REQUEST);
        }

        Pattern specialCharPattern = Pattern.compile("[^ㄱ-ㅎ가-힣a-zA-Z0-9\\s\\(\\)\\[\\]+&\\-/_]");
        if (specialCharPattern.matcher(productName).find()) {
            return new ResponseEntity<>("Invalid characters in name", HttpStatus.BAD_REQUEST);
        }

        if (productName.contains("카카오")) {
            return new ResponseEntity<>("Name contains restricted word '카카오'", HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productRepository.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        String productName = product.name();
        if (productName.length() > 15) {
            return new ResponseEntity<>("Name must be less than or equal to 15 characters", HttpStatus.BAD_REQUEST);
        }

        Pattern specialCharPattern = Pattern.compile("[^ㄱ-ㅎ가-힣a-zA-Z0-9\\s\\(\\)\\[\\]+&\\-/_]");
        if (specialCharPattern.matcher(productName).find()) {
            return new ResponseEntity<>("Invalid characters in name", HttpStatus.BAD_REQUEST);
        }

        if (productName.contains("카카오")) {
            return new ResponseEntity<>("Name contains restricted word '카카오'", HttpStatus.BAD_REQUEST);
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
}