package gift.Repository;

import gift.Service.ProductService;
import gift.model.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    // 상품 저장소
    public static final Map<Long, Product> products = new HashMap<>();

    public ProductRepository() {
        Product product1 = new Product("아이스 아메리카노 T", 5500, "testImageUrl.com");
        Product product2 = new Product("아이스 카푸치노 M", 5200, "testImageUrl.com");
        Product product3 = new Product("핫 초코 프라푸치노 M", 6000, "testImageUrl.com");

        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
    }
}
