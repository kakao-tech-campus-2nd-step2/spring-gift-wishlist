package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private ProductRepository productRepository = ProductRepository.getInstance();

    @GetMapping("/api/products")
    public ResponseEntity<Product> getProduct(@RequestParam Long id) {
        return ResponseEntity.ok().body(productRepository.getProduct(id));
    }

    @PostMapping("/api/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productRepository.addProduct(product));
    }

    @PatchMapping("/api/products")
    public ResponseEntity<Product> updateProduct(@RequestParam Long id, @RequestBody Product product) {
        return ResponseEntity.ok().body(productRepository.updateProduct(id, product));
    }

    @DeleteMapping("/api/products")
    public ResponseEntity<Product> deleteProduct(@RequestParam Long id) {
        return ResponseEntity.ok().body(productRepository.deleteProduct(id));
    }
}
