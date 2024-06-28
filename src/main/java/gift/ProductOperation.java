package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductOperation {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductOperation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product createProduct(Product p) {
        String sql = "INSERT INTO product (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, p.getId(), p.getName(), p.getPrice(), p.getImageUrl());
        return p;
    }

    public Product getProductById(long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        String sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, updatedProduct.getName(), updatedProduct.getPrice(), updatedProduct.getImageUrl(), id);
        return updatedProduct;
    }

    public boolean deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

}