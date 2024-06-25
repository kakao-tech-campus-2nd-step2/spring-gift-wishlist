package gift.web.controller;

import gift.service.ProductService;
import gift.web.dto.request.CreateProductRequest;
import gift.web.dto.request.UpdateProductRequest;
import gift.web.dto.response.CreateProductResponse;
import gift.web.dto.response.ReadAllProductsResponse;
import gift.web.dto.response.UpdateProductResponse;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //todo 응답을 ok에서 created로 변경
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(
        @RequestBody CreateProductRequest request) {
        CreateProductResponse response = productService.createProduct(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ReadAllProductsResponse> readAllProducts() {
        ReadAllProductsResponse response = productService.readAllProducts();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest request) {
        UpdateProductResponse response;
        try {
            response = productService.updateProduct(id, request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        boolean isSuccessful = productService.deleteProduct(id);
        if (isSuccessful) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
