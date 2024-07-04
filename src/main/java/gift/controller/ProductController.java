package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDTO) {
        ProductResponseDto createdProductDTO = productService.addProduct(productRequestDTO);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDTO) {
        ProductResponseDto updatedProductDTO = productService.updateProduct(id, productRequestDTO);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> productDTOs = productService.getAllProducts();
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
