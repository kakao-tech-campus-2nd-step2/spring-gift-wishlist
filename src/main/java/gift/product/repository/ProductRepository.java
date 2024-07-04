package gift.product.repository;

import gift.product.dto.LoginMember;
import gift.product.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.juli.logging.Log;
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

    public Product save(Product product, LoginMember loginMember) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        var sql = "INSERT INTO Product (member_id, name, price, imageUrl) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                    sql, new String[]{"id"}
                );
                pstmt.setLong(1, loginMember.memberId());
                pstmt.setString(2, product.getName());
                pstmt.setInt(3, product.getPrice());
                pstmt.setString(4, product.getImageUrl());

                return pstmt;
            }
        }, keyHolder);

        return new Product(keyHolder.getKey().longValue(), product.getName(), product.getPrice(),
            product.getImageUrl());
    }

    public List<Product> findAll(LoginMember loginMember) {
        var sql = "SELECT id, name, price, imageUrl FROM Product WHERE member_id = ?";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Product product = new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            );
            return product;
        }, loginMember.memberId());
    }

    public Product findById(Long id, LoginMember loginMember) {
        var sql = "SELECT id, name, price, imageUrl FROM Product WHERE member_id = ? AND id = ?";

        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> {
            Product product = new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            );
            return product;
        }, loginMember.memberId(), id);
    }

    public void update(Product product, LoginMember loginMember) {
        var sql = "UPDATE Product SET name = ?, price = ?, imageUrl = ? WHERE member_id = ? AND id = ?";

        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(),
            loginMember.memberId(), product.getId());
    }

    public void delete(Long id, LoginMember loginMember) {
        var sql = "DELETE FROM Product WHERE member_id = ? AND id = ?";

        jdbcTemplate.update(sql, loginMember.memberId(), id);
    }
}
