package gift.controller;

import gift.dto.ProductResponse;
import gift.model.Product;
import gift.dto.ProductRequest;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody ProductRequest productRequest) {
        var product = service.addProduct(productRequest);
        return ResponseEntity.created(URI.create("/api/products/"+product.getId())).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        var product = service.updateProduct(id, productRequest);
        return ResponseEntity.ok(ProductResponse.from(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        var product = service.getProduct(id);
        return ResponseEntity.ok(ProductResponse.from(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        var products = service.getProducts();
        return ResponseEntity.ok(products.stream().map(ProductResponse::from).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}