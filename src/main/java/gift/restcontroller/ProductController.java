package gift.restcontroller;

import gift.dto.RequestProductDto;
import gift.dto.RequestProductIdsDto;
import gift.dto.ResponseProductDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDto> getProduct(@PathVariable Long id) {
        ResponseProductDto responseProductDto = productService.getProduct(id);
        return ResponseEntity.ok(responseProductDto);

    }

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody @Valid RequestProductDto giftDto) {
        Long createdId = productService.createProduct(giftDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateProduct(@RequestBody @Valid RequestProductDto giftDto, @PathVariable Long id) {
        Long updatedId = productService.updateProduct(giftDto, id);
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id) {
        Long deletedId = productService.deleteProduct(id);
        return ResponseEntity.ok(deletedId);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProducts(@RequestBody @Valid RequestProductIdsDto ids) {
        productService.deleteProducts(ids.productIds());
        return ResponseEntity.ok().build();
    }

}
