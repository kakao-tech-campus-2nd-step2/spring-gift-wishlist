package gift.controller.api;

import gift.repository.ProductRepository;
import gift.dto.Product;
import gift.dto.ProductId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("api/products")
    public ResponseEntity<ProductId> addProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(new ProductId(productRepository.addProduct(product)), HttpStatus.CREATED);
    }

    @GetMapping("api/products")
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @PutMapping("api/products")
    public ResponseEntity<ProductId> updateProduct(@Valid @RequestBody Product product) {
        boolean isUpdated = productRepository.updateProduct(product);
        if (isUpdated) {
            return new ResponseEntity<>(new ProductId(product.getId()), HttpStatus.OK);
        }

        Long createdProductId = productRepository.addProduct(product);
        if (createdProductId != -1L) {
            return new ResponseEntity<>(new ProductId(createdProductId), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("api/products/{id}")
    public ResponseEntity<ProductId> deleteProduct(@PathVariable("id") Long id) {
        boolean isDeleted = productRepository.deleteProduct(id);
        if (isDeleted) {
            return new ResponseEntity<>(new ProductId(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
