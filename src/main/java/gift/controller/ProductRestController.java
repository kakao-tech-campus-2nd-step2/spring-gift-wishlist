package gift.controller;

import gift.db.ProductDB;
import gift.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestController {

    @Autowired
    private ProductDB productDB;

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
    public ResponseEntity<Map<String, String>> updateProduct(@RequestBody Product product) {
        Map<String, String> response = new HashMap<>();
        try {
            productDB.editProduct(product);
            response.put("message", "상품 수정 성공");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "상품 수정 실패");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
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
