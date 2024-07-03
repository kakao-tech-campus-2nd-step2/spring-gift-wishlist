package gift.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper()).stream().findFirst();
    }

    public void save(User user) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    public void updateUserToken(Long userId, String token) {
        String sql = "UPDATE users SET token = ? WHERE id = ?";
        jdbcTemplate.update(sql, token, userId);
    }

    public Optional<User> findByToken(String token) {
        String sql = "SELECT * FROM users WHERE token = ?";
        return jdbcTemplate.query(sql, new Object[]{token}, new UserRowMapper()).stream().findFirst();
    }

    public void addGiftToUser(Long userId, Long giftId) {
        String sql = "INSERT INTO user_gifts (user_id, gift_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, giftId);
    }

    public void removeGiftFromUser(Long userId, Long giftId) {
        String sql = "DELETE FROM user_gifts WHERE user_id = ? AND gift_id = ?";
        jdbcTemplate.update(sql, userId, giftId);
    }



    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setToken(rs.getString("token"));
            return user;
        }
    }
}
