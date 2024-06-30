package gift.controller;

import gift.model.ProductRecord;
import gift.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/products")
    public ProductRecord[] getAllProducts() {
        return productDAO.getAllRecords();
    }

    @PostMapping("/products")
    public ResponseEntity<ProductRecord> addProduct(@RequestBody ProductRecord product) {
        ProductRecord result = productDAO.addNewRecord(product);

        return makeCreatedResponse(result);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDAO.deleteRecord(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductRecord> updateProduct(@PathVariable int id, @RequestBody ProductRecord product) {
        ProductRecord result;
        try {
            result = productDAO.replaceRecord(id, product);

            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            result =  productDAO.addNewRecord(product, id);

            return makeCreatedResponse(result);
        }
    }

    @PatchMapping("/products/{id}")
    public ProductRecord updateProductPartially(@PathVariable int id, @RequestBody ProductRecord patch) {
        return productDAO.updateRecord(id, patch);
    }

    private ResponseEntity<ProductRecord> makeCreatedResponse(ProductRecord product) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/products/"+ product.id())
                .build()
                .toUri();

        return ResponseEntity.created(location).body(product);
    }
}
