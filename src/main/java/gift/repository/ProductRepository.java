package gift.repository;

import gift.domain.Product;
import gift.dto.ProductRequestDto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Product> PRODUCT_ROW_MAPPER = (resultSet, rowNum) -> new Product(
        resultSet.getLong("id"),
        resultSet.getString("name"),
        resultSet.getLong("price"),
        resultSet.getString("imageUrl")
    );

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, PRODUCT_ROW_MAPPER);
    }

    public Optional<Product> findById(Long id) {
        try {
            String sql = "SELECT * FROM products WHERE id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, PRODUCT_ROW_MAPPER, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Optional<Product> findByValues(ProductRequestDto product) {
        try {
            String sql = "SELECT * FROM products WHERE name = ? AND price = ? AND imageUrl = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, PRODUCT_ROW_MAPPER,
                product.name(),
                product.price(),
                product.imageUrl()
            ));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Product save(ProductRequestDto product) {
        String sql = "INSERT INTO products (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl());
        return findByValues(product).get();
    }

    public Product update(Long id, ProductRequestDto product) {
        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl(), id);
        return findByValues(product).get();
    }

    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
