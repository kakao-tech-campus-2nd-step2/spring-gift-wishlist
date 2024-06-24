package gift;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService = new ProductService();

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/create")
    public void addProduct(@RequestBody Product product) {

        productService.addProduct(product);
    }

    @PostMapping("/delete")
    public void deleteProduct(@RequestBody String name) {
        productService.deleteProduct(name);
    }

    @PostMapping("/update")
    public void updateProduct(String name, Product product) {
        productService.updateProduct(name, product);
    }

    @GetMapping("/products/{name}")
    public Product getProductByName(String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

}

