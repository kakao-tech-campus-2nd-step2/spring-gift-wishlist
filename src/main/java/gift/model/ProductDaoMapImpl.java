package gift.model;

import gift.controller.ProductRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ProductDaoMapImpl implements ProductDao {

    private final Map<Long, Product> database = new ConcurrentHashMap<>();

    @Override
    public void save(Product product) {
        Product newProduct = Product.create(Long.valueOf(database.size() + 1), product.getName(),
            product.getPrice(), product.getImageUrl());
        database.put(newProduct.getId(), newProduct);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(database.values());
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }

    @Override
    public void update(Long id, Product product) {
        database.replace(id, product);
    }

}
