package gift;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
        this.productDao.createProductTable();
    }

    @PostMapping
    public Product addProduct(@RequestBody Map<String, Object> productData) {
        String name = (String) productData.get("name");
        int price = (Integer) productData.get("price");
        String imageUrl = (String) productData.get("imageUrl");
        Product product = new Product(null, name, price, imageUrl);
        productDao.insertProduct(product);
        Long productId = productDao.selectAllProducts().stream().mapToLong(p -> p.id).max().orElseThrow();
        return new Product(productId, name, price, imageUrl);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productDao.selectAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Map<String, Object> productData) {
        String name = (String) productData.get("name");
        int price = (Integer) productData.get("price");
        String imageUrl = (String) productData.get("imageUrl");
        Product product = productDao.selectProduct(id);
        if (product == null) {
            return null;
        }
        product = new Product(id, name, price, imageUrl);
        productDao.updateProduct(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        Product product = productDao.selectProduct(id);
        if (product == null) {
            return false;
        }
        productDao.deleteProduct(id);
        return true;
    }
}
