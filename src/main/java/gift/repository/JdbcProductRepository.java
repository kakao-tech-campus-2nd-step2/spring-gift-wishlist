package gift.repository;

import gift.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
    Product product = new Product();
    product.setId(rs.getLong("id"));
    product.setName(rs.getString("name"));
    product.setPrice(rs.getInt("price")); // Use int for price
    product.setImageUrl(rs.getString("image_url")); // Use correct column name
    return product;
  };

  public List<Product> findAll() {
    String sql = "SELECT * FROM products";
    return jdbcTemplate.query(sql, productRowMapper);
  }

  public Product findById(Long id) {
    String sql = "SELECT * FROM products WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, productRowMapper, id);
  }

  public int save(Product product) {
    String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
    return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
  }

  public int update(Product product) {
    String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
    return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
  }

  public int deleteById(Long id) {
    String sql = "DELETE FROM products WHERE id = ?";
    return jdbcTemplate.update(sql, id);
  }
}
