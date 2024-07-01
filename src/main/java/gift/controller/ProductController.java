package gift.controller;

import gift.dao.ProductDao;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.ui.Model;
import gift.vo.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private final ProductDao dao;

    public ProductController(JdbcClient jdbcClient) {
        dao = new ProductDao(jdbcClient);
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> productList = new ArrayList<>(products.values());
        model.addAttribute("products", productList);
        return "index";
    }

    /**
     * 상품 조회 - 전체
     * @return 전체 상품 목록
     */
    @GetMapping()
    public List<Product> getAllProducts() {
        return dao.getProducts();
    }

    /**
     * 상품 조회 - 한 개
     * @param id 조회할 상품의 ID
     * @return 조회한 product
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Long id) {
        Product product = dao.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * 상품 추가
     * @param product 추가할 상품 (JSON 형식)
     * @return ResponseEntity로 Response 받음
     */
    @PostMapping()
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        Boolean result = dao.addProduct(product);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    /**
     * 상품 수정
     * @param product 수정할 상품 (JSON 형식)
     *    ㄴ받는 Product에 id 필드 값이 포함 되어 있어야 한다.
     * @return 수정된 상품
     */
    @PutMapping()
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        Boolean result = dao.updateProduct(product);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 상품 삭제
     * @param id 삭제할 상품의 ID
     * @return HTTP State - No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Long id) {
        Boolean result = dao.deleteProduct(id);
        if (result) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 상품 존재 여부 확인
     * @param id Product Id
     * @return 존재하면 true, 그렇지 않으면 false
     */
    public boolean productExists(Long id) {
        return products.containsKey(id);
    }

}
