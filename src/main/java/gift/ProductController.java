package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    private static final String ADD_SUCCESS_MSG = "상품 추가 성공";

    Product initialProduct = new Product(
            8146027L,
            "아이스 카페 아메리카노 T",
            4500,
            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    );

    private final Map<Long, Product> products = new HashMap<>();

    public ProductController() {
        products.put(initialProduct.getId(), initialProduct);
    }

    @GetMapping("/api/products/{productId}")
    public Product retrieveProduct(@PathVariable("productId") Long productId) {
        return getProduct(productId);
    }

    @PostMapping("/api/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(addNewProduct(product));
    }

    public Product getProduct(Long productId) {
        return products.get(productId);
    }

    public String addNewProduct(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
        return ADD_SUCCESS_MSG;
    }

}

