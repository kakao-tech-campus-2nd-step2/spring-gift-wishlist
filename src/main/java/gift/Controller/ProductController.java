package gift.Controller;

import gift.Model.Product;

import gift.Repository.ProductRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product newProduct) {
        return productRepository.saveProduct(newProduct);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable(value = "id") long id) {
        return productRepository.findProductsById(id);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productRepository.findProductsAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(value = "id") long id) {
        productRepository.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product updatedProduct) {
        productRepository.updateProduct(updatedProduct, id);
        return productRepository.findProductsById(id);
    }
}
