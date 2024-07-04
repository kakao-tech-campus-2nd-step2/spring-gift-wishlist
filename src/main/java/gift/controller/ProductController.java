package gift.controller;

import gift.domain.IdentifiedProductDto;
import gift.domain.UnidentifiedProductDto;
import gift.service.ProductService;
import java.util.List;
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
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<IdentifiedProductDto> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentifiedProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.find(id));
    }

    @PostMapping
    public ResponseEntity<IdentifiedProductDto> postProduct(@RequestBody UnidentifiedProductDto product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdentifiedProductDto> putProduct(@PathVariable Long id, @RequestBody UnidentifiedProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
