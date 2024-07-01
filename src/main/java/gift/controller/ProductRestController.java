package gift.controller;

import gift.db.ProductH2DB;
import gift.dto.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestController {

    private final ProductH2DB productDB;

    public ProductRestController(ProductH2DB productDB) {
        this.productDB = productDB;
    }

    @PostMapping("api/products")
    public ResponseEntity<Map<String, String>> addProduct(@RequestBody Product product) {
        Map<String, String> response = new HashMap<>();
        try {
            productDB.addProduct(product);
            response.put("message", "상품 추가 성공");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "상품 추가 실패");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("api/products")
    public List<Product> getProducts() {
        return productDB.getProducts();
    }

    @PutMapping("api/products/")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        boolean isUpdated = productDB.updateProduct(product);
        if (isUpdated) {
            return new ResponseEntity<>("Update Ok", HttpStatus.OK);
        }

        boolean isCreated = productDB.addProduct(product);
        if (isCreated) {
            return new ResponseEntity<>("Creat Ok", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Fail", HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("api/products/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable("id") Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            productDB.removeProduct(id);
            response.put("message", "상품 삭제 성공");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "상품 삭제 실패");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
