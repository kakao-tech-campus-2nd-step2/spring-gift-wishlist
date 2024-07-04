package gift.repository;

import gift.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void signUp(User user) {
        String sql = "INSERT INTO users(email, password) values (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    public User signIn(User user) {
        String sql = "SELECT email,password FROM users WHERE email=? and password=?";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) ->
            new User(
                resultSet.getString("email"),
                resultSet.getString("password")
            ), user.getEmail(), user.getPassword()
        );
    }
}
