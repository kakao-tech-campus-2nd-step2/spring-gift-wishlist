package gift.controller;

import gift.DTO.ProductDTO;
import gift.domain.Product;
import gift.domain.Product.ProductSimple;
import gift.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProductList() {
        return ResponseEntity.ok(productService.getProductList());
    }

    @GetMapping("/simple")
    public ResponseEntity<List<ProductSimple>> getSimpleProductList() {
        return ResponseEntity.ok(productService.getSimpleProductList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public void createProduct(@Validated @RequestBody Product.CreateProduct create) {
        productService.createProduct(create);
    }

    @PutMapping("/{id}")
    public void updateProduct(@Validated @RequestBody Product.UpdateProduct update,
        @PathVariable long id) {
        productService.updateProduct(update, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
