package gift.service;

import gift.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("SELECT * FROM products", (rs, rowNum) ->
                new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price"), rs.getString("image_url"))
        );
    }

    public void saveProduct(Product product) {
        if (product.getId() == 0) {
            Number newId = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource()
                    .addValue("name", product.getName())
                    .addValue("price", product.getPrice())
                    .addValue("image_url", product.getImageUrl()));
            product.setId(newId.longValue());
        } else {
            jdbcTemplate.update("UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?",
                    product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
        }
    }

    public Product getProductById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?", new Object[]{id}, (rs, rowNum) ->
                new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price"), rs.getString("image_url"))
        );
    }

    public void deleteProductById(long id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
    }
}
