package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    private static final String ADD_SUCCESS_MSG = "상품 추가 성공";
    private static final String UPDATE_SUCCESS_MSG = "상품 수정 성공";


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

    public Product getProduct(Long productId) {
        return products.get(productId);
    }

    @PostMapping("/api/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(addNewProduct(product));
    }

    public String addNewProduct(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
        return ADD_SUCCESS_MSG;
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {
        return ResponseEntity.ok(updateProductInfo(productId, product));
    }

    public String updateProductInfo(Long productId, Product product) {

        Product productToUpdate = products.get(productId);

        if (product.getName() != null) {
            productToUpdate.setName(product.getName());
        }
        if (product.getPrice() > 0) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getImageUrl() != null) {
            productToUpdate.setImageUrl(product.getImageUrl());
        }

        return UPDATE_SUCCESS_MSG;
    }

}

