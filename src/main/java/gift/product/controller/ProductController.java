package gift.product.controller;

import gift.product.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("/api")
public class ProductController {
    private final Map<Long, Product> productMap = new HashMap<>();

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productMap.get(id);
    }
}
