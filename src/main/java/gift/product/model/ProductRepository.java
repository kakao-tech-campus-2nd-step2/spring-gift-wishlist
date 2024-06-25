package gift.product.model;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 0L;

    public Product findProduct(Long id) throws Exception {
        validateProductId(id);
        return products.get(id); // ID가 유효한 경우, 상품 반환
    }

    public void addProduct(Product product) throws Exception {
        product.setId(generateNextId()); // 새 ID 할당
        products.put(product.getId(), product);
    }

    private synchronized Long generateNextId() {
        return ++nextId;
    }

    // 상품 ID 유효성 검증 메서드
    private void validateProductId(Long id) throws Exception {
        if (!products.containsKey(id)) {
            throw new Exception("존재하지 않는 상품 ID 입니다.");
        }
    }
}ㅎ
