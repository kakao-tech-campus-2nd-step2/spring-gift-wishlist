package gift.repository;

import gift.dto.UserDTO;
import gift.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String userInsertSql = "INSERT INTO USERS (email, password) VALUES (?, ?)";
    private static final String userSelectSql = "SELECT email, password FROM USERS WHERE email = ?";
    private static final String userCountSql = "SELECT COUNT(*) FROM USERS WHERE email = ?";

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUser(User user) {
        jdbcTemplate.update(userInsertSql, user.getEmail(), user.getPassword());
    }

    public UserDTO selectUser(String email) {
        User user = jdbcTemplate.queryForObject(
                userSelectSql, new Object[]{email}, (resultSet, rowNum) -> {
                    User userEntity = new User(
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                    return userEntity;
                });

        return new UserDTO(user.getEmail(), user.getPassword());
    }

    public Optional<Integer> countUsers(String email) {
        Integer count = jdbcTemplate.queryForObject(userCountSql, new Object[]{email}, Integer.class);

        return Optional.ofNullable(count);
    }

}
