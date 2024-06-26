package gift;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private static ProductRepository instance;
    private final Map<Long, Product> products = new HashMap<>();
    private Long idGenerator = 0L;

    private ProductRepository() {
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();

            // 임시 데이터
            instance.products.put(8146027L, new Product("아이스 카페 아메리카노 T", 4500,
                    "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        }
        return instance;
    }

    public Product addProduct(Product product) {
        products.put(idGenerator++, product);
        return product;
    }

    public Product getProduct(Long id) {
        return products.get(id);
    }

    public Product updateProduct(Long id, Product product) {
        products.put(id, product);
        return product;
    }

    public Product deleteProduct(Long id) {
        return products.remove(id);
    }
}
