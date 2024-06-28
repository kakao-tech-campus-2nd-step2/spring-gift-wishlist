package gift.repository;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("imageURL")
    );

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products", productRowMapper);
    }

    public Optional<Product> findById(Long id) {
        List<Product> results = jdbcTemplate.query("SELECT * FROM products WHERE id = ?", productRowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            jdbcTemplate.update("INSERT INTO products (name, price, imageURL) VALUES (?, ?, ?)",
                product.getName(), product.getPrice(), product.getImageURL());
        } else {
            jdbcTemplate.update("UPDATE products SET name = ?, price = ?, imageURL = ? WHERE id = ?",
                product.getName(), product.getPrice(), product.getImageURL(), product.getId());
        }
        return product;
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
    }

    public boolean existsById(Long id) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM products WHERE id = ?", Integer.class, id);
        return count != null && count > 0;
    }
}
