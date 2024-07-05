package gift.product;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }

    @PostMapping
    public void addProduct(
        @Valid @RequestBody ProductDTO productDto) {
        productRepository.addProduct(productDto);
    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable(value = "id") Long id,
        @Valid @RequestBody ProductDTO productDto) {
        productRepository.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(value = "id") Long id) {
        productRepository.deleteProduct(id);
    }
}
