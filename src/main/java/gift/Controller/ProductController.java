package gift.Controller;

import gift.Service.ProductService;
import gift.dto.ProductDTO;
import gift.model.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;

    // 상품 저장소
    private static final Map<Long, Product> products = new HashMap<>();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 추가
     *
     * @param productDTO git      * @return 추가 성공 시 추가된 상품 정보, 실패 시 실패 메시지
     */
    @PostMapping
    public ResponseEntity<Object> postProduct(@RequestBody ProductDTO productDTO) {
        return productService.postProduct(productDTO);
    }

    /**
     * 상품 목록 전체 조회
     *
     * @return products (상품 목록)
     */
    @GetMapping
    public ResponseEntity<Object> getProducts() {
        return productService.getProducts();
    }

    /**
     * 특정 ID 값의 상품 조회
     *
     * @param id
     * @return product (해당 ID 를 가진 상품)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    /**
     * 상품 내용 수정
     *
     * @param id
     * @param productDTO
     * @return product (수정된 상품 정보)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Long id,
        @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    /**
     * 모든 상품 삭제
     *
     * @return 삭제 완료 메시지
     */
    @DeleteMapping
    public ResponseEntity<Object> deleteAllProducts() {
        return productService.deleteAllProducts();
    }

    /**
     * 해당 ID 를 가진 상품 삭제
     *
     * @param id
     * @return product (삭제된 상품 정보)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }


}
