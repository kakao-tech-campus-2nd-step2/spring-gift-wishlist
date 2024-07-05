package gift;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductService(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
        ));
    }

    public Product getProductById(long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
        ));
    }

    public void addProduct(Product product) {
        try {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", product.getName());
            parameters.put("price", product.getPrice());
            parameters.put("image_url", product.getImageUrl());
            Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
            product.setId(newId.longValue());
        } catch (KakaoNameException e) {
            throw new RuntimeException("contack md", e);
        }
    }

    public void updateProduct(long id, Product product) {
        try {
            String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
            jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
        } catch (KakaoNameException e) {
            throw new RuntimeException("contack md", e);
        }
    }

    public void deleteProduct(long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
