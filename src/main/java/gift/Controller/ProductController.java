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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    // 상품 저장소
    private static final Map<Long, Product> products = new HashMap<>();

    /**
     * 상품 추가 기능
     *
     * @return Product 객체의 JSON 정보를 담은 ResponseEntity
     */
    @PostMapping("/products")
    public ResponseEntity<Object> postProduct(ProductDTO productDTO) {
        Product product = new Product(
            productDTO.getName(),
            productDTO.getPrice(),
            productDTO.getImageUrl()
        );
        if (!existSameProduct(product)){
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
     * @param product
     * @return 동일한 이름을 가진 product 가 이미 존재하면 false, 그렇지 않으면 true
     */
    public static boolean existSameProduct(Product product) {
        for (Product p : products.values()) {
            if (Objects.equals(product.getName(), p.getName())) {
                System.out.println("p Name = " + p.getName() + " product name: "+ product.getName());
                return true;
            }
        }
        return false;
    }
}
