package gift.domain.product;

import gift.domain.user.UserInfo;
import gift.global.jwt.JwtAuthorization;
import gift.global.response.ResponseMaker;
import gift.global.response.ResultResponseDto;
import gift.global.response.SimpleResultResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 추가
     *
     * @param productDTO
     * @return 결과 메시지
     */
    @PostMapping
    public ResponseEntity<SimpleResultResponseDto> createProduct(
        @Valid @ModelAttribute ProductDTO productDTO) {
        productService.createProduct(productDTO);
        return ResponseMaker.createSimpleResponse(HttpStatus.CREATED, "상품이 추가되었습니다.");
    }

    /**
     * 전체 상품 목록 조회
     *
     * @return 결과 메시지, products (상품 목록)
     */
    @GetMapping
    public ResponseEntity<ResultResponseDto<List<Product>>> getProducts() {
        List<Product> products = productService.getProducts();
        // 성공 시
        return ResponseMaker.createResponse(HttpStatus.OK, "전체 목록 상품을 조회했습니다.", products);
    }


    /**
     * 상품 수정
     *
     * @param id
     * @param productDTO
     * @return 결과 메시지
     */
    @PutMapping("/{id}")
    public ResponseEntity<SimpleResultResponseDto> updateProduct(@PathVariable("id") Long id,
        @Valid @RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return ResponseMaker.createSimpleResponse(HttpStatus.OK, "상품을 수정했습니다.");
    }


    /**
     * 해당 ID 리스트에 속한 상품 삭제
     *
     * @param productIds
     * @return 결과 메시지
     */
    @DeleteMapping
    public ResponseEntity<SimpleResultResponseDto> deleteSelectedProducts(
        @RequestBody List<Long> productIds) {
        productService.deleteProductsByIds(productIds);
        return ResponseMaker.createSimpleResponse(HttpStatus.OK, "선택된 상품들을 삭제했습니다.");
    }

    /**
     * 해당 ID 를 가진 상품 삭제
     *
     * @param id
     * @return 결과 메시지
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResultResponseDto> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseMaker.createSimpleResponse(HttpStatus.OK, "상품이 삭제되었습니다.");
    }


    @PostMapping("/cart/{id}")
    public ResponseEntity<SimpleResultResponseDto> addProductToCart(
        @PathVariable("id") Long productId,
        @JwtAuthorization UserInfo userInfo) {
        log.info("userInfo = {}, productId = {}", userInfo, productId);
        productService.addProductToCart(userInfo.getId(), productId);
        return ResponseMaker.createSimpleResponse(HttpStatus.OK, "상품이 장바구니에 추가되었습니다.");
    }

    @GetMapping("/cart")
    public ResponseEntity<ResultResponseDto<List<Product>>> getProductsInCartByUserId(
        @JwtAuthorization UserInfo userInfo) {
        log.info("get products in cart by userId");
        List<Product> products = productService.getProductsInCartByUserId(
            userInfo.getId());

        return ResponseMaker.createResponse(HttpStatus.OK, "장바구니 조회에 성공했습니다.", products);
    }
}
