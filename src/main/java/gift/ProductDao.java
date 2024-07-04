package gift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private ProductDTO mapRowToProduct(ResultSet resultSet, int rowNum) throws SQLException {
        return new ProductDTO(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("imageUrl")
        );
    }

    public void insertProduct(ProductDTO product) {
        String sql = "INSERT INTO product (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public ProductDTO selectProduct(long id) {
        String sql = "SELECT id, name, price, imageUrl FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToProduct, id);
    }

    public void updateProduct(ProductDTO product) {
        String sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

    public void deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<ProductDTO> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, this::mapRowToProduct);
    }
}
