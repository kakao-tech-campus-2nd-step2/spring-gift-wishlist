package gift.repository;

import gift.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(long id);
    List<Product> findAll();
    Boolean deleteById(long id);
    Boolean updateById(long id, Product product);
    Boolean save(Product product);

    //Optional<Void> update(long id, Product product);
    //Optional<Void> save(Product product); //Optional으로 warpping

}
