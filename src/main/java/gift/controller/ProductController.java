package gift.controller;

import org.springframework.web.bind.annotation.*;
import gift.model.Product;
import gift.model.ProductDao;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productDao.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = productDao.getProduct(id);
        return product;
    }

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productDao.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productDao.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productDao.updateProduct(id, product);
    }
}
