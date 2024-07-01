package gift.infra;

import gift.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Product> getProducts() {
        String sql = "SELECT * FROM Product";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public Product getProductByName(String name) {
        String sql = "SELECT * FROM Product WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new ProductRowMapper());
    }

    public Product getProductById(Long id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public void deleteProduct(String name) {
        String sql = "DELETE FROM Product WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public void updateProduct(String name, Product product) {
        String sql = "UPDATE Product SET id = ?, price = ?, imageUrl = ? WHERE name = ?";
        jdbcTemplate.update(sql, product.getId(), product.getPrice(), product.getImageUrl(), name);
    }
}
