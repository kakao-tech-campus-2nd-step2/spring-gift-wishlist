package gift.repository;

import gift.domain.Product;

public interface ProductRepository {
    Product getProductById(Long id);

    Long saveProduct(Product product);

    Long updateProduct(Long id, Product product);

    void deleteProductById(Long id);
}
