package gift.repository;

import gift.model.Product;
import java.util.Map;

import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertProduct(Product product) {
        var sql = "INSERT INTO products (id, name, price, image_url) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(),
            product.getImageUrl());
    }

    public void updateProduct(Product product) {
        var sql = "UPDATE products SET name=?, price=?, image_url=? WHERE id=?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(),
            product.getId());
    }

    public void deleteProduct(Long id) {
        var sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Product selectProduct(Long id) {
        var sql = "SELECT id, name, price, image_url FROM products WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) -> new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("image_url")
                ),
                id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Map<Long, Product> selectAllProducts() {
        var sql = "SELECT id, name, price, image_url FROM products";
        var productList = jdbcTemplate.query(sql,
            (resultSet, rowNum) -> new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("image_url")
            ));
        return productList.stream().collect(Collectors.toMap(Product::getId, x -> x));
    }

    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM products WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
