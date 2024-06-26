package gift.product;

import gift.product.model.GetProductRes;
import gift.product.model.PatchProductReq;
import gift.product.model.PostProductReq;
import gift.product.model.ProductRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductRepository productRepository = new ProductRepository();

    @GetMapping("/product")
    public ResponseEntity<GetProductRes> findProductById(@RequestParam(value = "id") Long id) {
        final GetProductRes response = productRepository.findProduct(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/products")
    public ResponseEntity<List<GetProductRes>> findAllProduct() {
        final List<GetProductRes> response = productRepository.findAllProduct();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/product")
    public ResponseEntity<Long> addProduct(@Valid @RequestBody PostProductReq postProductReq) {
        Long id = productRepository.addProduct(postProductReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping("/product")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody PatchProductReq patchProductReq) {
        productRepository.updateProduct(patchProductReq);
        return ResponseEntity.ok().body("ok");
    }

    @PatchMapping("/product/deleted")
    public ResponseEntity<String> deleteProductById(@RequestParam(value = "product") Long id) {
        productRepository.deleteProduct(id);
        return ResponseEntity.ok().body("ok");
    }
}
