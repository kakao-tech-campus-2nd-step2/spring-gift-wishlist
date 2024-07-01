package gift.db;

import gift.dto.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Qualifier("memoryDatabase")
public class ProductMemoryDB implements ProductDB {

    private final Map<Long, Product> products = new HashMap<>();

    ProductMemoryDB() {
        products.put(1L, new Product(1L, "Americano", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        products.put(2L, new Product(2L, "Latte", 5500, "https://cdn.pixabay.com/photo/2023/07/08/13/17/coffee-8114518_1280.png"));
        products.put(3L, new Product(3L, "Sandwich", 7700, "https://cdn.pixabay.com/photo/2023/08/12/02/58/sandwich-8184642_1280.png"));
        products.put(4L, new Product(4L, "Cup Cake", 10000, "https://cdn.pixabay.com/photo/2023/05/31/14/41/ai-generated-8031574_1280.png"));
    }

    @Override
    public void addProduct(Product product) {

        products.putIfAbsent(product.getId(), product);

    }

    @Override
    public Product getProduct(Long id) {
        return products.get(id);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(entry.getValue());
        }
        return productList;
    }

    @Override
    public void removeProduct(Long id) throws Exception {
        if (!products.containsKey(id)) {
            throw new Exception();
        }
        products.remove(id);
    }

    @Override
    public void removeProducts(List<Long> productIds) {
        for (Long id : productIds) {
            products.remove(id);
        }
    }

    @Override
    public void editProduct(Product product) throws Exception {
        if (!hasProductId(product.getId())) {
            throw new Exception();
        }
        products.put(product.getId(), product);
    }

    public boolean hasProductId(Long id) {
        return products.containsKey(id);
    }

}
