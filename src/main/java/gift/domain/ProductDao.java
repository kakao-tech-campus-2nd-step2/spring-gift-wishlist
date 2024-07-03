package gift.domain;

import java.util.List;

public interface ProductDao {
    void save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    void update(Product product,Long id);
    void deleteById(Long id);


}
