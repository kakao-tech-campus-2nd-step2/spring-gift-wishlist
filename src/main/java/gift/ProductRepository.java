package gift;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createProductTable();
    }

    private void createProductTable() {
        jdbcTemplate.execute("""
                    CREATE TABLE product(
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        price INT,
                        imageUrl VARCHAR(255))
                """);
    }

    public Product addProduct(String name, Integer price, String imageUrl) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO product(name, price, imageUrl) VALUES(?, ?, ?)",
                    new String[] { "id" });
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, imageUrl);
            return ps;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            return new Product(keyHolder.getKey().longValue(), name, price, imageUrl);
        }
        return null;
    }

    public List<Product> getAllProduct() {
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

    public Product deleteProduct(Long id) {
        Product result = jdbcTemplate.queryForObject(
                "SELECT * FROM product WHERE id = ?",
                (rs, rowNum) -> new Product(rs.getLong("id"),
                        rs.getString("name"), rs.getInt("price"), rs.getString("imageUrl")),
                id);

        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
        return result;
    }
}
