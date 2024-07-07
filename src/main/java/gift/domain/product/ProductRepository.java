package gift.domain.product;

import java.util.List;

public interface ProductRepository {

    boolean existsByProductName(String name);

    void createProduct(Product product);

    List<Product> getProducts();

    void updateProduct(Long id, Product product);

    void deleteProductByIds(List<Long> productIds);

    void deleteProduct(Long id);

}
