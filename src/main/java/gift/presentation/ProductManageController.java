package gift.presentation;

import gift.application.ProductService;
import gift.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductManageController {

    private final ProductService productService;

    @Autowired
    public ProductManageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String name) {
        productService.deleteProduct(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{name}")
    public ResponseEntity<Object> updateProduct(@PathVariable String name, @RequestBody Product product) {
        productService.updateProduct(name, product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
