package gift;

import java.util.List;

public interface ProductRepository {
    void save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    void update(Product product);
    void delete(Long id);
}