package gift.model;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface ProductDao {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    Product update(Long id, Product product);
}
