package gift.controller.restcontroller;

import gift.controller.dto.ProductRequest;
import gift.controller.dto.ProductResponse;
import gift.model.Product;
import gift.model.ProductDao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    private final ProductDao productDao;

    public ProductRestController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<Product> products = productDao.findAll();
        List<ProductResponse> responses = products.stream().map(
                ProductResponse::from
        ).toList();
        return ResponseEntity.ok().body(responses);
    }

    @Validated
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id")
                                                          @NotNull(message = "ID cannot be null")
                                                          @Min(value = 1, message = "ID must be at least 1")
                                                          Long id) {
        Product product = productDao.findById(id);
        ProductResponse response = ProductResponse.from(product);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/product")
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest request) {
        productDao.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Long> updateProduct(@PathVariable("id") @NotNull @Min(1) Long id,
                                              @RequestBody ProductRequest request) {
        productDao.updateById(id, request);
        return ResponseEntity.ok().body(id);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") @NotNull @Min(1) Long id) {
        productDao.deleteById(id);
        return ResponseEntity.ok().body(id);
    }

}
