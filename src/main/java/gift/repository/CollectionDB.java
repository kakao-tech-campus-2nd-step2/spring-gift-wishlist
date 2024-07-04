package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CollectionDB {
    private Map<Long, Product> products =new HashMap<>(); //여기에 final 붙이는게 맞나요?

    public CollectionDB() {}

    public Map<Long, Product> getProducts() {
        return products;
    }
    public void setProducts(Map<Long,Product> products){
        this.products = products;
    }
    public void saveProduct(Long id, Product product){
        products.put(id,product);
    }
    public Map<Long,Product> findAll() {
        return products;
    }
}
