package gift.repository;

import gift.exception.UserErrorCode;
import gift.exception.UserException;
import gift.model.User;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;
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

    public User selectUserByEmail(String email) throws UserException{
        try {
            return jdbcTemplate.queryForObject(
                UserQuery.SELECT_USER_BY_EMAIL.getQuery(),
                (resultSet, rowNum) -> new User(
                    resultSet.getString("email"),
                    resultSet.getString("password")
                ),
                email
            );
        } catch (DataRetrievalFailureException e) {
            throw new UserException(UserErrorCode.NOT_AUTHENTICATION);
        }
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
