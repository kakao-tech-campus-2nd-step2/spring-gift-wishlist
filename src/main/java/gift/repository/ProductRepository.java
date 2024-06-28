package gift.repository;

import gift.converter.StringToUrlConverter;
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
        return Optional.ofNullable(jdbcTemplate.queryForObject("select id, name, price, image_url from product where id = ?",
            (rs, rowNum) -> new Product.Builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .imageUrl(rs.getURL("image_url"))
                .build(), id));
    }

    public List<Product> findAll() {
        StringToUrlConverter converter = new StringToUrlConverter();
        return jdbcTemplate.query("select id, name, price, image_url from product",
            (rs, rowNum) -> new Product.Builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .imageUrl(converter.convert(rs.getString("image_url")))
                .build());
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
