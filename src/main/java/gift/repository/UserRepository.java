package gift.repository;

import gift.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_USER_SQL = "INSERT INTO users (email, password) VALUES (?, ?)";

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        jdbcTemplate.update(INSERT_USER_SQL, user.getEmail(), user.getPassword());
    }
}