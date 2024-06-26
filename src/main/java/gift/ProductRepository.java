package gift;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private static ProductRepository instance;
    private final Map<Long, Product> productsMap = new HashMap<>();
    private Long idGenerator = 0L;

    private ProductRepository() {
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public Product addProduct(String name, Integer price, String imageUrl) {
        productsMap.put(idGenerator, new Product(idGenerator, name, price, imageUrl));
        return productsMap.get(idGenerator++);
    }

    public List<Product> getAllProduct() {
        return productsMap.values().stream().toList();
    }

    public Product updateProduct(Long id, String name, Integer price, String imageUrl) {
        return productsMap.put(id, new Product(id, name, price, imageUrl));
    }

    public Product deleteProduct(Long id) {
        return productsMap.remove(id);
    }
}
