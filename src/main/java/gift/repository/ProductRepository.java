package gift.repository;

import gift.domain.Product;
import gift.domain.dto.ProductUpdateParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    //todo remove
    private final Map<Long, Product> products = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(Product product) {
        String sql = "insert into product (name, price, image_url) values (?, ?, ?)";
        jdbcTemplate.update(sql,
            product.getName(), product.getPrice(), product.getImageUrl());
        return product.getId(); //todo 올바른 아이디가 반환되는지 확인
    }

    public Optional<Product> findById(Long id) {
        return Optional.of(products.get(id));
    }

    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    public boolean deleteById(Long id) {
        if (existsById(id)) {
            products.remove(id);
            return true;
        }
        return false;
    }

    public Product update(Long id, ProductUpdateParam productUpdateParam) {
        Product product = products.get(id).update(productUpdateParam);
        products.put(id, product);
        return product;
    }

    public boolean existsById(Long id) {
        return products.containsKey(id);
    }

}
