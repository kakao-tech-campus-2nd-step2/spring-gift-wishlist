package gift.repository;

import gift.dto.UserDTO;
import gift.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUser(User user) {
        var sql = "INSERT INTO USERS (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    public UserDTO selectUser(String email) {
        String sql = "select email, password from USERS where email = ?";
        User user = jdbcTemplate.queryForObject(
                sql, new Object[]{email}, (resultSet, rowNum) -> {
                    User userEntity = new User(
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                    return userEntity;
                });

        return new UserDTO(user.getEmail(), user.getPassword());
    }

    public Optional<Integer> countUsers(String email) {
        String sql = "select count(*) from USERS where email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);

        return Optional.ofNullable(count);
    }

}
