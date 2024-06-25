package gift.repository;

import gift.domain.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<UUID, Product> products = new HashMap<>();

    public UUID save(Product product) {
        products.put(product.getId(), product);
        return product.getId();
    }

    public Product findById(UUID id) {
        return products.get(id);
    }

    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    public void delete(UUID id) {
        products.remove(id);
    }

    public void update(UUID id, Product product) {
        products.put(id, product);
    }

}
