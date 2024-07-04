package gift.entity;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insertUser(User user) {
        var sql = "insert into user (email, password) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.email);
            ps.setString(2, user.password);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Optional<User> selectUserByEmail(String email) {
        var sql = "select id, email, password from user where email = ?";
        List<User> users = jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                ),
                email
        );
        return users.stream().findFirst();
    }
}
