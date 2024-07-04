package gift.controller;

import gift.DTO.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    // 생성자를 사용하여 ProductRepository 초기화
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 모든 상품 조회
     *
     * @return 모든 상품 목록
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productRepository.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * id로 특정 상품 조회
     *
     * @param id 조회할 상품의 id
     * @return 조회된 상품 객체와 200 OK, 해당 id가 없으면 404 NOT FOUND
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 새로운 상품 추가
     *
     * @param product 추가할 상품
     * @return 같은 ID의 상품이 존재하지 않으면 201 Created, 아니면 400 Bad Request
     */
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        if (isProductExists(product.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
        productRepository.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED); // 201 Created
    }

    /**
     * 상품 삭제
     *
     * @param id 삭제할 상품의 id
     * @return 삭제에 성공하면 204 NO CONTENT, 해당 ID의 상품이 없으면 404 NOT FOUND
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!isProductExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 상품 정보 수정
     *
     * @param id             수정할 상품의 id
     * @param updatedProduct 새로운 상품 객체
     * @return 상품 정보 수정에 성공하면 200 OK, 해당 id의 상품이 없으면 404 NOT FOUND
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
        @RequestBody Product updatedProduct) {
        if (!isProductExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
        productRepository.updateProduct(updatedProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // 200 OK
    }

    private boolean isProductExists(Long id) {
        return productRepository.getProductById(id).isPresent();
    }
}
