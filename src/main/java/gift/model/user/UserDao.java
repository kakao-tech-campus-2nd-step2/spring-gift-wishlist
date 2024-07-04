package gift.model.user;

import gift.enums.ReadQuery;
import gift.model.product.Product;
import gift.model.product.ProductRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(UserRequest userRequest) {
        String sql = "INSERT INTO users (password, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, userRequest.password(), userRequest.email());

        User user = findByEmail(userRequest.email());
        return user;
    }

    public User findByEmail(String email) {
        var sql = ReadQuery.FIND_BY_EMAIL.getQuery();
        return jdbcTemplate.queryForObject(
            sql,
            (resultSet, rowNum) -> new User(
                resultSet.getString("password"),
                resultSet.getString("email")
            ),
            email
        );
    }
}
