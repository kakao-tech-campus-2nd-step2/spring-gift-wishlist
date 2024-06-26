package gift.repository;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
