package gift.product.controller;

import gift.product.model.ProductDao;
import gift.product.model.dto.GetProductRes;
import gift.product.model.dto.PatchProductReq;
import gift.product.model.dto.PostProductReq;
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
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }


    @GetMapping("/product")
    public ResponseEntity<GetProductRes> findProductById(@RequestParam(value = "id") Long id) {
        final GetProductRes response = productDao.findProduct(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/products")
    public ResponseEntity<List<GetProductRes>> findAllProduct() {
        final List<GetProductRes> response = productDao.findAllProduct();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@Valid @RequestBody PostProductReq postProductReq) {
        if (productDao.addProduct(postProductReq) > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ok");
        }
        throw new IllegalArgumentException("상품 추가 실패");
    }

    @PatchMapping("/product")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody PatchProductReq patchProductReq) {
        if (productDao.updateProduct(patchProductReq) > 0) {
            return ResponseEntity.ok().body("ok");
        }
        throw new IllegalArgumentException("상품 수정 실패");
    }

    @PatchMapping("/product/deleted")
    public ResponseEntity<String> deleteProductById(@RequestParam(value = "id") Long id) {
        if (productDao.deleteProduct(id) > 0) {
            return ResponseEntity.ok().body("ok");
        }
        throw new IllegalArgumentException("상품 삭제 실패");
    }
}
