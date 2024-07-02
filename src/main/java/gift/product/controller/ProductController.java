package gift.product.controller;

import gift.product.model.ProductRepository;
import gift.product.model.dto.GetProductRes;
import gift.product.model.dto.PatchProductReq;
import gift.product.model.dto.PostProductReq;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products ")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/product-id")
    public ResponseEntity<GetProductRes> findProductById(@RequestParam(value = "id") Long id) {
        final GetProductRes response = productRepository.findProduct(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<GetProductRes>> findAllProduct() {
        final List<GetProductRes> response = productRepository.findAllProduct();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<String> addProduct(@Valid @RequestBody PostProductReq postProductReq) {
        if (productRepository.addProduct(postProductReq) > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ok");
        }
        throw new IllegalArgumentException("상품 추가 실패");
    }

    @PutMapping("")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody PatchProductReq patchProductReq) {
        if (productRepository.updateProduct(patchProductReq) > 0) {
            return ResponseEntity.ok().body("ok");
        }
        throw new IllegalArgumentException("상품 수정 실패");
    }

    @PutMapping("deleted")
    public ResponseEntity<String> deleteProductById(@RequestParam(value = "id") Long id) {
        if (productRepository.deleteProduct(id) > 0) {
            return ResponseEntity.ok().body("ok");
        }
        throw new IllegalArgumentException("상품 삭제 실패");
    }
}
