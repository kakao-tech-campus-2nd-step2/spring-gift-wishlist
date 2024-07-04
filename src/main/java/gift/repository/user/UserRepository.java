package gift.repository.user;

import gift.domain.Product;
import gift.domain.User;
import java.util.List;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByEmail(String email) {
        var sql = "select * from user where email = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, email);
        return users.isEmpty() ? null : users.get(0);
    }

    public void save(User user) {
        var sql = "insert into user (email, password) values (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
        rs.getString("email"),
        rs.getString("password")
    );

}
