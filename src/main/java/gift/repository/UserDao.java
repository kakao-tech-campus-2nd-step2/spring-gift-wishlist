package gift.repository;

import gift.exception.UserErrorCode;
import gift.exception.UserException;
import gift.model.User;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
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
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("role")
            )
        );
    }

    public User selectUserByEmail(String email) throws UserException {
        try {
            return jdbcTemplate.queryForObject(
                UserQuery.SELECT_USER_BY_EMAIL.getQuery(),
                (resultSet, rowNum) -> new User(
                    resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("name"),
                    resultSet.getString("role")
                ),
                email
            );
        } catch (EmptyResultDataAccessException e) {
            throw new UserException(UserErrorCode.FAILURE_LOGIN);
        }
    }

    public User selectUserById(Long id) throws EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject(
            UserQuery.SELECT_USER_BY_ID.getQuery(),
            (resultSet, rowNum) -> new User(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("role")
            ),
            id
        );
    }

    public void insertUser(User user) {
        jdbcTemplate.update(UserQuery.INSERT_USER.getQuery(), user.getEmail(), user.getPassword(),
            user.getName(), user.getRole());
    }

    public void updateUserByEmail(String email, User user) {
        jdbcTemplate.update(UserQuery.UPDATE_USER_BY_EMAIL.getQuery(), user.getEmail(),
            user.getPassword(), user.getName(), user.getRole(), email);
    }

    public void deleteUserByEmail(String email) {
        jdbcTemplate.update(UserQuery.DELETE_USER_BY_EMAIL.getQuery(), email);
    }
}
