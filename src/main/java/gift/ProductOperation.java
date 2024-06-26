package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ProductOperation {

    private final Map<Long, Product> productMap = new HashMap<>();

    public Product createProduct(Product p) {
        Product newProduct = new Product(p.id(), p.name(), p.price(), p.imageUrl());
        productMap.put(newProduct.id(), newProduct);
        return newProduct;
    }

    public Product getProductById(long id) {
        return productMap.get(id);
    }

    public Map<Long, Product> getAllProduct() {
        return productMap;
    }

    public Product updateProduct(Long id, Product p) {
        if (productMap.containsKey(id)) {
            Product newProduct = new Product(id, p.name(), p.price(), p.imageUrl());
            productMap.put(newProduct.id(), newProduct);
            return newProduct;
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        return productMap.remove(id) != null;
    }

}
