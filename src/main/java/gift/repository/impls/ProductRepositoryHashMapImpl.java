package gift.repository.impls;

import gift.domain.Product;
import gift.repository.ProductRepository;

import java.util.*;

public class ProductRepositoryHashMapImpl implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private static Long id = 0L;

    @Override
    public Long save(Product product){
        id += 1;
        Product newProduct = new Product(id,product.getName(),product.getPrice(),product.getImageUrl());
        products.put(id, newProduct);
        return id;
    }

    @Override
    public Optional<Product> findById(Long id){
       return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll(){
        return new ArrayList<>(products.values());
    }

    @Override
    public Long delete(Long id){
        products.remove(id);
        return id;
    }

    @Override
    public Product update(Long id, Product product){
        products.remove(id);
        Product editProduct = new Product(id,product.getName(), product.getPrice(), product.getImageUrl());
        products.put(id, editProduct);
        return editProduct;
    }
}
