package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
/*
@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
    }

    @Override
    public void update(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void delete(Long id) {
        products.remove(id);
    }
}


 */