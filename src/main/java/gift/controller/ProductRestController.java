package gift.controller;

import gift.db.ProductH2DB;
import gift.dto.Product;
import gift.response.ResponseProductId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    private final ProductH2DB productDB;

    public ProductRestController(ProductH2DB productDB) {
        this.productDB = productDB;
    }

    @PostMapping("api/products")
    public ResponseEntity<ResponseProductId> addProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(new ResponseProductId(productDB.addProduct(product)), HttpStatus.CREATED);
    }

    @GetMapping("api/products")
    public List<Product> getProducts() {
        return productDB.getProducts();
    }

    @PutMapping("api/products")
    public ResponseEntity<ResponseProductId> updateProduct(@Valid @RequestBody Product product) {
        boolean isUpdated = productDB.updateProduct(product);
        if (isUpdated) {
            return new ResponseEntity<>(new ResponseProductId(product.getId()), HttpStatus.OK);
        }

        Long createdProductId = productDB.addProduct(product);
        if (createdProductId != -1L) {
            return new ResponseEntity<>(new ResponseProductId(createdProductId), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("api/products/{id}")
    public ResponseEntity<ResponseProductId> deleteProduct(@PathVariable("id") Long id) {
        boolean isDeleted = productDB.deleteProduct(id);
        if (isDeleted) {
            return new ResponseEntity<>(new ResponseProductId(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
