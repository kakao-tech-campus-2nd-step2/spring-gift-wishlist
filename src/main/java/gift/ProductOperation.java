package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ProductOperation {

    private final Map<Long, Product> productMap = new HashMap<>();

    public Product createProduct(Product p) {
        productMap.put(p.getId(), p);
        return p;
    }

    public Product getProductById(long id) {
        return productMap.get(id);
    }

    public Map<Long, Product> getAllProduct() {
        return productMap;
    }

    public Product updateProduct(Long id, Product p) {
        if (productMap.containsKey(id)) {
            productMap.put(id, p);
            return p;
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        return productMap.remove(id) != null;
    }

}