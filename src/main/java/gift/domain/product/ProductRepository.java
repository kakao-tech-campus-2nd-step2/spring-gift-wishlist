package gift.domain.product;

import java.util.List;

public interface ProductRepository {

    boolean existsByProductName(String name);

    void createProduct(ProductDTO productDTO);

    List<Product> getProducts();

    void updateProduct(Long id, ProductDTO productDTO);

    void deleteProductByIds(List<Long> productIds);

    void deleteProduct(Long id);

}
