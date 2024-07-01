package gift.product.repository;

import gift.product.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        var sql = "INSERT INTO Product (name, price, imageUrl) VALUES (?, ?, ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                    sql, new String[]{"id"}
                );
                pstmt.setString(1, product.getName());
                pstmt.setInt(2, product.getPrice());
                pstmt.setString(3, product.getImageUrl());

                return pstmt;
            }
        }, keyHolder);

        return new Product(keyHolder.getKey().longValue(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public List<Product> findAll() {
        var sql = "SELECT id, name, price, imageUrl FROM Product";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Product product = new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            );
            return product;
        });
    }

    public Product findById(Long id) {
        var sql = "SELECT id, name, price, imageUrl FROM Product WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> {
            Product product = new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            );
            return product;
        }, id);
    }

    public void update(Product product) {
        var sql = "UPDATE Product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";

        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(),
            product.getId());
    }

    public void delete(Long id) {
        var sql = "DELETE FROM Product WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }
}
