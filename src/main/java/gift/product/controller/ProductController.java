package gift.product.controller;

import gift.product.dto.ProductRequest;
import gift.product.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> productMap = new HashMap<>();

    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productMap.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id) {
        return productMap.get(id);
    }

    @PostMapping("")
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        Long id = addProduct(productRequest);
        return productMap.get(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductRequest productRequest) {
        productMap.replace(id, new Product(id, productRequest));
        return productMap.get(id);
    }

    @DeleteMapping("/{id}")
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
