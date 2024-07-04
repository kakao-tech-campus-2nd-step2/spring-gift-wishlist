package gift.repository;

import gift.Product;
import gift.dto.ProductUpdateRequestDTO;
import jakarta.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Product product) {
        String sql = "INSERT INTO products_tb (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products_tb";
        return jdbcTemplate.query(sql, productRowMapper());
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM products_tb WHERE id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, productRowMapper(), id);
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void update(ProductUpdateRequestDTO product) {
        String sql = "UPDATE products_tb SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM products_tb WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Product> productRowMapper() {
        return new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setImageUrl(rs.getString("imageUrl"));
                return product;
            }
        };
    }
}
