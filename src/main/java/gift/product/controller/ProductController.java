package gift.product.controller;

import gift.product.dto.ProductRequest;
import gift.product.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> productMap = new HashMap<>();

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id) {
        return productMap.get(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        Long id = addProduct(productRequest);
        return productMap.get(id);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductRequest productRequest) {
        productMap.replace(id, new Product(id, productRequest));
        return productMap.get(id);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productMap.remove(id);
        return "product " + id + " is deleted";
    }

    private Long addProduct(ProductRequest productRequest) {
        Long id = getMaxKey();
        productMap.put(id, new Product(id, productRequest));
        return id;
    }

    private Long getMaxKey() {
        return 1L + productMap.keySet().stream()
                .max(Long::compare)
                .orElse(0L);
    }
}
