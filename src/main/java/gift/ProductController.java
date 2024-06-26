package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    Product newProduct = new Product(
            8146027L,
            "아이스 카페 아메리카노 T",
            4500,
            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    );

    private final Map<Long, Product> products = new HashMap<>();

    public ProductController() {
        products.put(newProduct.getId(), newProduct);
    }

    @GetMapping("/api/products/{productId}")
    public Product retrieveProduct(@PathVariable("productId") Long productId) {
        return getProduct(productId);
    }

    public Product getProduct(Long productId) {
        return products.get(productId);
    }

}

