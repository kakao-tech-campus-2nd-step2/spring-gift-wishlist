package gift.feat.product.repository;

import java.util.List;
import java.util.Optional;
import gift.feat.product.domain.Product;

public interface ProductRepository {
	void save(Product product);
	Optional<Product> findById(Long id);
	List<Product> findAll();
	void update(Product product);
	void deleteById(Long id);
}