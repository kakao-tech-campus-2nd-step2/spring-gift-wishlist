package gift.controller;

import gift.model.ProductDao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        var response = productDao.findAll().stream()
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
        productDao.insert(request.toEntity());
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

    @GetMapping("/products/paging")
    public ResponseEntity<List<ProductResponse>> getProductsPaging(@RequestParam("page") int page,
        @RequestParam("size") int size) {
        var products = productDao.findPaging(page, size);
        var response = products.stream()
            .map(ProductResponse::from)
            .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/count")
    public ResponseEntity<Long> getProductsCount() {
        return ResponseEntity.ok(productDao.count());
    }
}
