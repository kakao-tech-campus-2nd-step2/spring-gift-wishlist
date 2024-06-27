package gift.repository;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
        return product;
    }

    public void delete(Long id) {
        products.remove(id);
    }

    public boolean existsById(Long id) {
        return products.containsKey(id);
    }
}
