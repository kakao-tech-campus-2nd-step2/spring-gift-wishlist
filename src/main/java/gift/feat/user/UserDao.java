package gift.feat.user;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDao {
	private final JdbcTemplate jdbcTemplate;
	private final ObjectMapper objectMapper;

	public User save(User user) {
		String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			var ps = connection.prepareStatement(sql, new String[] { "id" });
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole());
			return ps;
		}, keyHolder);
		Long generatedId = keyHolder.getKey().longValue();
		return User.of(generatedId, user.getEmail(), user.getPassword(), user.getRole());
	}

	public User findByEmail(String email) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?", (rs, rowNum) ->
			User.of(rs.getLong("id"), rs.getString("email"), rs.getString("password"), rs.getString("role")), email);
	}

	public void update(User user) {
		String sql = "UPDATE users SET email = ?, password = ?, role = ? WHERE id = ?";
		jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getRole(), user.getId());
	}

	public List<User> findAll() {
		return jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) ->
			User.of(rs.getLong("id"), rs.getString("email"), rs.getString("password"), rs.getString("role")));
	}
}
