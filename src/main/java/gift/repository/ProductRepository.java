package gift.repository;

import gift.model.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );

    public ProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("product")
            .usingGeneratedKeyColumns("id");
    }
    // 지인이 동시성 문제를 해결하기 위해 ConcurrentHashMap 이라는 것을 사용해보자고 해서...
    // 좀 더 알아봐야겠다!
    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    // 자동으로 제품 ID를 등록하기 위해
    private final AtomicLong idGenerator = new AtomicLong(0);

    public void save(Product product){
        if (product.getId() == null) {
            Map<String, Object> params = new HashMap<>();
            params.put("name", product.getName());
            params.put("price", product.getPrice());
            params.put("image_url", product.getImageUrl());
            Number newId = simpleJdbcInsert.executeAndReturnKey(params);
            product.setId(newId.longValue());
        }

        if (product.getId() != null){
            jdbcTemplate.update("UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?",
                product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
        }
    }

    public Optional<Product> findById(Long id) {
        List<Product> results = jdbcTemplate.query("SELECT * FROM product WHERE id = ?", productRowMapper, id);
        return results.stream().findFirst();
    }

    public Optional<Product> findByName(String name) {
        List<Product> results = jdbcTemplate.query("SELECT * FROM product WHERE name = ?", productRowMapper, name);
        return results.stream().findFirst();
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", productRowMapper);
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }
}
