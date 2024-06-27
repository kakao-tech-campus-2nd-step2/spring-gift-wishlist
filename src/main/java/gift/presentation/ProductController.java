package gift.presentation;

import gift.application.ProductService;
import gift.domain.Product;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService = new ProductService();

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteProduct(@RequestBody String name) {
        productService.deleteProduct(name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateProduct(String name, Product product) {
        productService.updateProduct(name, product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<Product> getProductByName(String name) {
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}

