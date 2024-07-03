package gift.controller;

import gift.model.Product;
import gift.model.ProductDao;
import gift.model.ProductRequest;
import gift.model.ProductResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductDao productDao;

    ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> registerProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = productDao.save(productRequest);
        ProductResponse response = ProductResponse.from(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> productList = productDao.findAll();
        List<ProductResponse> responses = productList.stream().map(ProductResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long id) {
        Product product = productDao.findById(id);
        ProductResponse response = ProductResponse.from(product);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id,
                                                         @Valid @RequestBody ProductRequest productRequest) {
        Product product = productDao.update(id, productRequest);
        ProductResponse response = ProductResponse.from(product);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        productDao.delete(id);
        return ResponseEntity.noContent().build();
    }
}

