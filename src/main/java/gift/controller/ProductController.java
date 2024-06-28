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
    public ResponseEntity<List<ProductResponse>> getProducts() {
        var products = productDao.findAll();
        var response = products.stream()
            .map(ProductResponse::from)
            .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long id) {
        var product = productDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        var response = ProductResponse.from(product);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        productDao.save(request.toEntity());
        return ResponseEntity.ok().body("Product created successfully.");
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long id,
        @RequestBody ProductRequest request) {
        productDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        productDao.update(request.toEntity(id));
        return ResponseEntity.ok().body("Product updated successfully.");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        productDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
