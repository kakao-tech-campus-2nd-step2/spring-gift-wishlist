package gift.repository;

import gift.domain.Product;

import java.util.*;

public class ProductMemoryRepository implements ProductRepository{

    private final static Map<Long, Product> products = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Product save(Product product) {
        product.setId(++sequence);
        products.put(sequence, product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Long update(Long id, int price) {
        Product product = products.get(id);
        product.setPrice(price);
        return id;
    }

    @Override
    public Long delete(Long id) {
        products.remove(id);
        return id;
    }

    //테스트용 Map 초기화 함수입니다.
    public void clear(){
        products.clear();
    }
}
