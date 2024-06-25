package gift.repository;

import gift.domain.Product;
import gift.domain.dto.ProductUpdateParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<UUID, Product> products = new HashMap<>();

    public UUID save(Product product) {
        products.put(product.getId(), product);
        return product.getId();
    }

    public Optional<Product> findById(UUID id) {
        return Optional.of(products.get(id));
    }

    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    public boolean deleteById(UUID id) {
        if (existsById(id)) {
            products.remove(id);
            return true;
        }
        return false;
    }

    public Product update(UUID id, ProductUpdateParam productUpdateParam) {
        Product product = products.get(id).update(productUpdateParam);
        products.put(id, product);
        return product;
    }

    public boolean existsById(UUID id) {
        return products.containsKey(id);
    }

}
