package gift.DAO;

import gift.DTO.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM Product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product findById(Long id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }

    public int save(Product product) {
        String sql = "INSERT INTO Product (name, price, imageUrl) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public int update(Product product) {
        String sql = "UPDATE Product SET id = ?, name = ?, price = ?, imageUrl = ? WHERE id = ?";
        return jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
