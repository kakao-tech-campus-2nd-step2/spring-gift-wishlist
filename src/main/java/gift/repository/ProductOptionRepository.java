package gift.repository;

import gift.model.ProductOption;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository {
    ProductOption save(ProductOption productOption);
    void update(ProductOption productOption);
    ProductOption findById(Long id);
    List<ProductOption> findAll(Long productId);
    void deleteById(Long id);
    void deleteByProductId(Long id);
}
