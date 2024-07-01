package gift;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        var sql = "select * from product";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) -> new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            )
        );
    }

    public Product findById(Long id) {
        var sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(
            sql,
            (resultSet, rowNum) -> new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            ),
            id);
    }

    public int insertProduct(Product product) {
        var sql = "INSERT INTO product (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public int updateProduct(Long id, Product product) {
        var sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    public int deleteById(Long id) {
        var sql = "DELETE FROM product WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
