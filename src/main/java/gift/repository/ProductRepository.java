package gift.repository;

import gift.domain.Product;
import java.util.HashMap;
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

}
