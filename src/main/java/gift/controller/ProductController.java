package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import gift.dto.ProductRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity makeProduct(@RequestBody @Valid ProductRequestDto requestDto) {
        productService.makeProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productsList = productService.getAllProducts();
        return ResponseEntity.ok().body(productsList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/products")
    public ResponseEntity putProduct(@RequestBody @Valid ProductRequestDto requestDto) {
        productService.putProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
