package gift.repository;

import gift.model.User;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> selectAllUsers() {
        return jdbcTemplate.query(
            UserQuery.SELECT_ALL_USER.getQuery(),
            (resultSet, rowNum) -> new User(
                resultSet.getString("email"),
                resultSet.getString("password")
            )
        );
    }

    public User selectUserByEmail(String email) {
        return jdbcTemplate.queryForObject(
            UserQuery.SELECT_USER_BY_EMAIL.getQuery(),
            (resultSet, rowNum) -> new User(
                resultSet.getString("email"),
                resultSet.getString("password")
            ));
    }

    public void insertUser(User user) {
        jdbcTemplate.update(UserQuery.INSERT_USER.getQuery(), user.getEmail(), user.getPassword());
    }

    public void updateUserByEmail(String email, User user) {
        jdbcTemplate.update(UserQuery.UPDATE_USER_BY_EMAIL.getQuery(), user.getEmail(),
            user.getPassword(), email);
    }

    public void deleteUserByEmail(String email) {
        jdbcTemplate.update(UserQuery.DELETE_USER_BY_EMAIL.getQuery(), email);
    }
}
