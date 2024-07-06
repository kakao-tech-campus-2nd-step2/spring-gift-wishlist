package gift.domain.product;

import gift.dto.ProductRequestDto;

import java.util.ArrayList;

public interface ProductRepository {
    void save(Product product);
    ArrayList<Product> findAll();
    Product findById(Long id);
    void deleteById(Long id);
    int update(Long id, ProductRequestDto dto);
    boolean isNotValidProductId(Long id);
}
