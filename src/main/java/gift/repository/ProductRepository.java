package gift.repository;

import gift.model.Product;
import java.util.Map;

public interface ProductRepository {

    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long id);

    Product selectProduct(Long id);

    Map<Long, Product> selectAllProducts();

    boolean existsById(Long id);
}
