package gift.product.controller;

import gift.product.dto.ProductRequest;
import gift.product.entity.Product;
import gift.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return productService.createProduct(productRequest.toServiceDto());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productRequest.toServiceDto(id));
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return "product " + id + " is deleted";
    }
}
