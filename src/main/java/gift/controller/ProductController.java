package gift.controller;

import gift.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    ProductController(){
        products.put(1L, new Product(1L, "AAA", 123, "adsfiewfjo"));
        products.put(2L, new Product(2L, "BBB", 456, "eroguhensjkn"));
    }

    // 상품 모두 조회
    @GetMapping("/api/products")
    public Map<Long, Product> responseAllProducts(){
        return products;
    }

    // 상품 하나 조회
    @GetMapping("/api/products/{id}")
    public ResponseEntity<?> responseOneProduct(@PathVariable("id") long id){
        if(products.containsKey(id)){
            return new ResponseEntity<>(products.get(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("ID not exists", HttpStatus.NOT_FOUND);
    }

    // 상품 추가
    @PostMapping("/api/products")
    public ResponseEntity<String> addOneProduct(@RequestBody Product product){
        if (products.containsKey(product.id())) {
            return new ResponseEntity<>("Product with ID " + product.id() + " already exists.", HttpStatus.CONFLICT);
        }
        products.put(product.id(), product);
        return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);

    }

    // 상품 수정
    @PostMapping("/api/products/{id}")
    public ResponseEntity<String> modifyOneProduct(@PathVariable("id") long id, @RequestBody Product product){
        if (products.containsKey(id)) {
            products.replace(id, product);
            return new ResponseEntity<>("Product with ID " + id + " modified.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product with ID " + id + " not exists.", HttpStatus.NOT_MODIFIED);
    }


}
