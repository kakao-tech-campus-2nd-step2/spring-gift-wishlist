package gift.Controller;

import gift.Model.ProductModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, ProductModel> products = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    private HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return new ResponseEntity<>(new ArrayList<>(products.values()), jsonHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable long id) {
        ProductModel product = products.get(id);
        if (product == null) {
            return new ResponseEntity<>(jsonHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, jsonHeaders(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
        long id = counter.incrementAndGet();
        product.setId(id);
        products.put(id, product);
        return new ResponseEntity<>(product, jsonHeaders(), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable long id, @RequestBody ProductModel product) {
        ProductModel existingProduct = products.get(id);
        if (existingProduct == null) {
            return new ResponseEntity<>(jsonHeaders(), HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        products.put(id, product);
        return new ResponseEntity<>(product, jsonHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        ProductModel product = products.remove(id);
        if (product == null) {
            return new ResponseEntity<>(jsonHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jsonHeaders(), HttpStatus.NO_CONTENT);
    }
}

