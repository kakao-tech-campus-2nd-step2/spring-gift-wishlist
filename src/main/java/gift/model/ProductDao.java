package gift.model;

import gift.controller.ProductRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {

    private final Map<Long, Product> database = new ConcurrentHashMap<>();

    public Product save(ProductRequest request) {
        Product product = Product.create(request.name(), request.price(), request.imageUrl());
        database.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<Product> findAll() {
        return List.copyOf(database.values());
    }

    public void deleteById(Long id) {
        database.remove(id);
    }

    public Product update(Long id, ProductRequest request) {
        Product product = Product.update(id, request.name(), request.price(), request.imageUrl());
        database.replace(id, product);
        return product;
    }

}
