package gift;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Product> productRowMapper = (Resultset, rowNum) -> {
        Product product = new Product();
        product.setId(Resultset.getLong("id"));
        product.setName(Resultset.getString("name"));
        product.setPrice(Resultset.getInt("price"));
        product.setImageUrl(Resultset.getString("imageUrl"));
        return product;
    };

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products", productRowMapper);
    }

    public Product findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?", productRowMapper, id);
    }

    public void save(Product product) {
        Map<String, Object> parameters = Map.of(
                "name", product.getName(),
                "price", product.getPrice(),
                "imageUrl", product.getImageUrl()
        );
        Number newId = jdbcInsert.executeAndReturnKey(parameters);
        product.setId(newId.longValue());
    }

    public void update(Long id, Product product) {
        product.setId(id);
        jdbcTemplate.update("UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?",
                product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
    }
}
