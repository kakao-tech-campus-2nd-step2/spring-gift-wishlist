package gift.repository;

import gift.model.Product;
import gift.model.ProductDto;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Product> getRowMapper() {
        return (resultSet, rowNum) -> new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getLong("price"),
            resultSet.getString("image_url")
        );
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        Product product = null;
        try {
            product =  jdbcTemplate.queryForObject(sql, getRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {}
        return Optional.ofNullable(product);
    }

    public Optional<Product> findByContents(ProductDto dto) {
        String sql = "SELECT * FROM products WHERE name = ? AND price = ? AND image_url = ?";
        Product product = null;
        try {
            product =  jdbcTemplate.queryForObject(sql, getRowMapper(), dto.name(), dto.price(), dto.imageUrl());
        } catch (EmptyResultDataAccessException e) { }
        return Optional.ofNullable(product);
    }

    public Product update(Long id, ProductDto product) {
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl(), id);
        return new Product(id, product.name(), product.price(), product.imageUrl());
    }

    public Product save(ProductDto product) {
        String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl());

        // Retrieve the generated id
        Long id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM products", Long.class);
        return new Product(id, product.name(), product.price(), product.imageUrl());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
