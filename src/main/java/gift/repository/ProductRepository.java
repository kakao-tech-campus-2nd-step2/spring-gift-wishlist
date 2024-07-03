package gift.repository;

import gift.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(long id);
    Optional<Product> findByName(String name);
    List<Product> findAll();
}
