package gift.product.controller;

import gift.product.Product;
import gift.product.exception.ProductCreateException;
import gift.product.exception.ProductDeleteException;
import gift.product.exception.ProductErrorCode;
import gift.product.exception.ProductUpdateException;
import gift.product.message.ProductInfo;
import gift.product.dto.ProductReqDto;
import gift.product.dto.ProductResDto;
import gift.product.repository.ProductRepository;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;

    private static final Integer NO_OF_ROWS_AFFECTED = 1;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResDto>> getProducts() {
        List<Product> products = productRepository.findProducts();

        List<ProductResDto> productResDtos = products.stream()
                .map(ProductResDto::new)
                .toList();

        return ResponseEntity.ok(productResDtos);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResDto> getProduct(@PathVariable Long productId) {
        Product product = productRepository.findProductById(productId);

        return ResponseEntity.ok(new ProductResDto(product));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResDto> addProduct(@Valid @RequestBody ProductReqDto productReqDto) {
        Long productId = productRepository.addProduct(productReqDto);

        if (productId == null || productId == 0L) {
            throw new ProductCreateException(ProductErrorCode.PRODUCT_CREATE_FAILED);
        }

        // 저장된 상품 가져오기
        Product newProduct = productRepository.findProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResDto(newProduct));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductReqDto productReqDto) {
        Integer noOfRowsAffected = productRepository.updateProductById(productId, productReqDto);

        if (NO_OF_ROWS_AFFECTED.equals(noOfRowsAffected)) {     // 수정된 행의 개수 반환 - 1이면 성공, 0이면 실패
            return ResponseEntity.ok(ProductInfo.PRODUCT_UPDATE_SUCCESS);
        }

        throw new ProductUpdateException(ProductErrorCode.PRODUCT_UPDATE_FAILED);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        Integer noOfRowsAffected = productRepository.deleteProductById(productId);

        if (NO_OF_ROWS_AFFECTED.equals(noOfRowsAffected)) {     // 삭제된 행의 개수 반환 - 1이면 성공, 0이면
            return ResponseEntity.ok(ProductInfo.PRODUCT_DELETE_SUCCESS);
        }

        throw new ProductDeleteException(ProductErrorCode.PRODUCT_DELETE_FAILED);
    }
}
