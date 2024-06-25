package gift.controller;

import gift.dto.CreateProductDto;
import gift.dto.ProductDto;
import gift.dto.UpdateProductDto;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductDto> getProduct(@RequestParam Long id) {
        ProductDto productDto = productService.getProduct(id);
        return ResponseEntity.ok(productDto);

    }

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody CreateProductDto giftDto) {
        Long createdId = productService.createProduct(giftDto);
        return ResponseEntity.ok(createdId);
    }

    @PutMapping
    public ResponseEntity<Long> updateProduct(@RequestBody UpdateProductDto giftDto) {
        Long updatedId = productService.updateProduct(giftDto);
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


}
