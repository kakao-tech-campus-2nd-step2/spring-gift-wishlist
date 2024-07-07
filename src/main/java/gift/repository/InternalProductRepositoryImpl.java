package gift.repository;

import gift.domain.Product;
import gift.exception.ProductNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InternalProductRepositoryImpl implements ProductRepository{
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.put(product.getId(),product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(Long id) throws ProductNotFoundException{
        validateProduct(id);
        return products.get(id);
    }

    @Override
    public Product updateProduct(Long id, Product updateProduct) throws ProductNotFoundException{
        Product product = findById(id);
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setImageUrl(updateProduct.getImageUrl());
        return product;
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException{
        validateProduct(id);
        products.remove(id);
    }

    private void validateProduct(Long id) throws ProductNotFoundException{
        Product product = products.get(id);
        if (product == null){
            throw new ProductNotFoundException("product is not found");
        }
    }

}
