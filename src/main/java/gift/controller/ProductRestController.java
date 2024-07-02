package gift.controller;

import gift.db.ProductH2DB;
import gift.dto.Product;
import gift.response.ResponseProductId;
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
    public ResponseEntity<ResponseProductId> addProduct(@RequestBody Product product) {
        productDB.addProduct(product);
        return new ResponseEntity<>(new ResponseProductId(productDB.getLatestProductId()), HttpStatus.CREATED);
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

        return new ResponseEntity<>("Failed to update product", HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("api/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        boolean isDeleted = productDB.deleteProduct(id);
        if (isDeleted) {
            return new ResponseEntity<>("Delete Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete product", HttpStatus.NO_CONTENT);
    }

}
