package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("url")
    );

    public List<Product> findAll() {
        String sql = "SELECT id, name, price, url FROM products";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    public void add(Product product) {

        String sql = "INSERT INTO products (id, name, price, url) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public Product findById(Long id) {
        String sql = "SELECT id, name, price, url FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, productRowMapper);
    }

    public void update(Long id, Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, url = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}