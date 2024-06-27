package gift.product.presentation;

import gift.product.presentation.dto.ProductRequest;
import gift.product.entity.Product;
import gift.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("")
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        Long id = addProduct(productRequest);
        return productService.createProduct();
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
