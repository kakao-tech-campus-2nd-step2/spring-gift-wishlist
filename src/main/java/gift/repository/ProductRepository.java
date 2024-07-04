package gift.repository;

import gift.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    Product save(Product product);
    void update(Product product);
    Product findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
