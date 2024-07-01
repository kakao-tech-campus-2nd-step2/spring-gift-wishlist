package gift.repository;

import gift.model.ProductDAO;
import gift.model.ProductDTO;

import java.util.List;

public interface ProductRepository {

    ProductDAO save(ProductDTO form);

    boolean delete(Long id);

    ProductDAO edit(Long id, ProductDTO form);

    ProductDAO findById(Long id);

    List<ProductDAO> findAll();
}
