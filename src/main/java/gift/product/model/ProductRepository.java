package gift.product.model;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public Long addProduct(PostProductReq newProduct) {
        Long id = nextId++;
        Product product = new Product(id, newProduct.name(), newProduct.price(), newProduct.imageUrl(), true);
        products.put(id, product);
        return id;
    }

    public GetProductRes findProduct(Long id) {
        Product product = validateProductId(id);
        return new GetProductRes(product.id, product.name, product.price, product.imageUrl);
    }

    public void updateProduct(PatchProductReq updatedProduct) {
        Product product = validateProductId(updatedProduct.id());
        if (updatedProduct.name() != null) {
            product.name = updatedProduct.name();
        }
        if (updatedProduct.price() != null) {
            product.price = updatedProduct.price();
        }
        if (updatedProduct.imageUrl() != null) {
            product.imageUrl = updatedProduct.imageUrl();
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
