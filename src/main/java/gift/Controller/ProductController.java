package gift.Controller;

import gift.dto.ProductDTO;
import gift.model.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

    // 상품 저장소
    private static final Map<Long, Product> products = new HashMap<>();

    /**
     * 상품 추가
     * @param productDTO
     * @return
     */
    @PostMapping("/products")
    public ResponseEntity<Object> postProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product(
            productDTO.getName(),
            productDTO.getPrice(),
            productDTO.getImageUrl()
        );
        if (!existProduct(productDTO.getName())){
            products.put(product.getId(), product);
            Product.increase();
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("해당 이름의 상품이 이미 존재합니다.");
    }

    /**
     * 상품 목록 전체 조회
     * @return products (상품 목록)
     */
    @GetMapping("/products")
    public ResponseEntity<Object> getProducts() {
        if(products.size() == 0){
            return ResponseEntity.ok("상품이 존재하지 않습니다.");
        }
        return ResponseEntity.ok(products);
    }

    /**
     * 특정 ID 값의 상품 조회
     * @param id
     * @return product (해당 ID 를 가진 상품)
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        if(product == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 상품이 존재하지 않습니다.");
        }
        return ResponseEntity.ok(product);
    }

    /**
     * 상품 내용 수정
     * @param id
     * @param productDTO
     * @return product (수정된 상품 정보)
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = products.get(id);
        if(product == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 상품이 존재하지 않습니다.");
        }
        if(existSameName(id, productDTO.getName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정할 이름을 가진 상품이 이미 존재합니다. 다른 이름을 입력하세요.");
        }
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        products.put(product.getId(), product);

        return ResponseEntity.ok(product);
    }

    /**
     * 모든 상품 삭제
     * @return 삭제 완료 메시지
     */
    @DeleteMapping("/products")
    public ResponseEntity<Object> deleteAllProducts(){
        products.clear();
        return ResponseEntity.ok("모든 상품을 삭제했습니다.");        
    }

    /**
     * 해당 ID 를 가진 상품 삭제
     * @param id
     * @return product (삭제된 상품 정보)
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        if(products.get(id) == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 상품이 존재하지 않습니다.");
        }
        Product removedProduct = products.remove(id);
        return ResponseEntity.ok(removedProduct);
    }

    /**
     * @param name
     * @return 해당 이름을 가진 product 가 상품 목록에 존재하면 true, 그렇지 않으면 false
     */
    public static boolean existProduct(String name) {
        for (Product p : products.values()) {
            if (Objects.equals(name, p.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 상품 이름 수정 시, 다른 상품들 중 해당 이름을 가진 상품이 있는지 확인
     * @param id, name
     * @return 상품 동일한 이름을 가진 product 가 이미 상품 목록에 존재하면 false, 그렇지 않으면 true
     */
    public static boolean existSameName(Long id, String name) {
        for (Product p : products.values()) {
            if (Objects.equals(name, p.getName()) && p.getId() != id) {
                return true;
            }
        }
        return false;
    }

}
