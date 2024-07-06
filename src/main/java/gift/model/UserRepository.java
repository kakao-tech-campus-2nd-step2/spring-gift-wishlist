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

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper()).stream().findFirst();
    }

    public void save(User user) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    public void addGiftToUser(Long userId, Long giftId) {
        String sql = "INSERT INTO user_gifts (user_id, gift_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, giftId);
    }

    public void removeGiftFromUser(Long userId, Long giftId) {
        String sql = "DELETE FROM user_gifts WHERE user_id = ? AND gift_id = ?";
        jdbcTemplate.update(sql, userId, giftId);
    }

    public List<Gift> getGiftsForUser(Long userId) {
        String sql = "SELECT g.* FROM gift g INNER JOIN user_gifts ug ON g.id = ug.gift_id WHERE ug.user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) ->
                new Gift(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("imageUrl")
                ));
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
