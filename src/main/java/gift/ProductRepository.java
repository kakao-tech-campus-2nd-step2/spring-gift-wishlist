package gift;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(String name, Integer price, String imageUrl) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO product(name, price, imageUrl) VALUES(?, ?, ?)",
                    new String[] { "id" });
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, imageUrl);
            return ps;
        });
    }

    public List<Product> getAllProducts() {
        return jdbcTemplate.query(
                "SELECT * FROM product",
                (rs, rowNum) -> new Product(rs.getLong("id"),
                        rs.getString("name"), rs.getInt("price"), rs.getString("imageUrl")));
    }

    public Product updateProduct(Long id, String name, Integer price, String imageUrl) {
        jdbcTemplate.update(
                "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?",
                name, price, imageUrl, id);
        return new Product(id, name, price, imageUrl);
    }

    public void deleteProduct(Long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }
}