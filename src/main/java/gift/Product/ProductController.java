package gift.Product;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }

    @PostMapping("/api/products")
    public void addProduct(
            @Valid @RequestBody(required = true) ProductDTO productDto) {
        productRepository.addProduct(productDto);
    }

    @PatchMapping("/api/products/{id}")
    public void updateProduct(@PathVariable(value = "id") Long id,
            @Valid @RequestBody(required = true) ProductDTO productDto) {
        productRepository.updateProduct(id, productDto);
    }

    @DeleteMapping("/api/products/{id}")
    public void deleteProduct(@PathVariable(value = "id") Long id) {
        productRepository.deleteProduct(id);
    }
}
