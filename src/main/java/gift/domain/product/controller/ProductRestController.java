package gift.domain.product.controller;

import gift.domain.product.dao.ProductDao;
import gift.domain.product.dto.ProductDto;
import gift.domain.product.entity.Product;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductDao productDao;

    public ProductRestController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDto productDto) {
        Product product = productDto.toProduct();
        Product savedProduct = productDao.insert(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll() {
        List<Product> productList = productDao.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> readById(@PathVariable("productId") long productId) {
        Optional<Product> product = productDao.findById(productId);

        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable("productId") long productId, @RequestBody @Valid ProductDto productDto) {
        Product product = productDto.toProduct();
        product.setId(productId);

        Optional<Product> updatedProduct = productDao.update(product);

        if (updatedProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct.get());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") long productId) {
        int nOfRowsAffected = productDao.delete(productId);

        if (nOfRowsAffected != 1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
