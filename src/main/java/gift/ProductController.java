package gift;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/products")
    public List<Product> getProduct() {
        return productRepository.getAllProduct();
    }

    @PostMapping("/api/products")
    public Product addProduct(
            @RequestParam("name") String name,
            @RequestParam("price") Integer price,
            @RequestParam("imageurl") String imageUrl) {
        return productRepository.addProduct(name, price, imageUrl);
    }

    @PatchMapping("/api/products")
    public Product updateProduct(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("price") Integer price,
            @RequestParam("imageurl") String imageUrl) {
        return productRepository.updateProduct(id, name, price, imageUrl);
    }

    @DeleteMapping("/api/products")
    public Product deleteProduct(@RequestParam("id") Long id) {
        return productRepository.deleteProduct(id);
    }
}
