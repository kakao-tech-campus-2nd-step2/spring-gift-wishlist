package gift.controller;

import gift.model.Product;
import gift.model.ProductDao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * admin 페이지로 이동합니다.
     *
     * @return
     */
    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        var response = productDao.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        var response = productDao.findById(id);
        return ResponseEntity.ok(response.orElse(null));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        productDao.save(request.toEntity());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
        @RequestBody ProductRequest request) {
        productDao.update(id, request.toEntity(id));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
