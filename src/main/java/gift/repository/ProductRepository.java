package gift.repository;

import gift.domain.Product;
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
        String sql = "SELECT * FROM kakaoProduct";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product findById(Long id) {
        String sql = "SELECT * FROM kakaoProduct WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }

    public void save(Product product) {
        if (product.getId() == null) {
            String sql = "INSERT INTO kakaoProduct (name, price, image_url) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
        } else {
            update(product);
        }
    }

    public void update(Product product) {
        String sql = "UPDATE kakaoProduct SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM kakaoProduct WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
