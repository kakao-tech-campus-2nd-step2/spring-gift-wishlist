package gift.product;

import gift.product.model.GetProductRes;
import gift.product.model.PatchProductReq;
import gift.product.model.PostProductReq;
import gift.product.model.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public Long addProduct(PostProductReq newProduct) {
        Long id = nextId++;
        Product product = new Product(id, newProduct.getName(), newProduct.getPrice(), newProduct.getImageUrl(), true);
        products.put(id, product);
        return id;
    }

    public GetProductRes findProduct(Long id) {
        Product product = validateProductId(id);
        return new GetProductRes(product.id, product.name, product.price, product.imageUrl);
    }

    public List<GetProductRes> findAllProduct() {
        return products.values().stream()
                .filter(product -> product.isActive)
                .map(product -> new GetProductRes(product.id, product.name, product.price, product.imageUrl))
                .toList();
    }

    public void updateProduct(PatchProductReq updatedProduct) {
        Product product = validateProductId(updatedProduct.getId());
        if (updatedProduct.getName() != null) {
            product.name = updatedProduct.getName();
        }
        if (updatedProduct.getPrice() != 0) {
            product.price = updatedProduct.getPrice();
        }
        if (updatedProduct.getImageUrl() != null) {
            product.imageUrl = updatedProduct.getImageUrl();
        }
    }

    public void deleteProduct(Long id) {
        Product product = validateProductId(id);
        product.isActive = false;
    }

    // 상품 ID 유효성 검증 메서드
    private Product validateProductId(Long id) throws IllegalArgumentException {
        if (products.containsKey(id)) {
            Product product = products.get(id);
            if (product.isActive) {
                return product;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 상품 ID 입니다.");
    }
}
