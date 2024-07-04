package gift.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        jdbcTemplate.update(
            "INSERT INTO USERS(EMAIL, PASSWORD) VALUES (?, ? )",
            user.email(), user.password()
        );
    }

    public Boolean existUserByEmail(String email) {
        Boolean existUser = jdbcTemplate.queryForObject(
            "SELECT EXISTS (SELECT * FROM USERS WHERE email = ?)",
            Boolean.class,
            email
        );
        return existUser;
    }
}
