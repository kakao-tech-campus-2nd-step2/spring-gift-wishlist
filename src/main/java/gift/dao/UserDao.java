package gift.dao;

import gift.domain.User;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void createUserTable() {
        String sql = """
              CREATE TABLE users(
              id bigint AUTO_INCREMENT,
              email varchar(255),
              password varchar(255),
              primary key(id)
            );
              """;

        jdbcTemplate.execute(sql);
    }

    public void signUp(User user) {
        String sql = "INSERT INTO users(email,password) values(?,?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    public User signIn(User user) {
        String sql = "SELECT email,password FROM users WHERE email=? and password=?";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) ->
                new User(
                    resultSet.getString("email"),
                    resultSet.getString("password")
                )
            , user.getEmail(), user.getPassword());
    }
}
