package gift.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import gift.model.User;
import jakarta.annotation.PostConstruct;

@Service
public class AuthService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcInsert jdbcInsert;
	
	@PostConstruct
	public void setup() {
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("users")
				.usingGeneratedKeyColumns("id");
	}
	
	private static final class UserMapper implements RowMapper<User>{
		@Override
		public User mapRow(ResultSet resultSet, int rowNum) throws SQLException{
			User user = new User();
			user.setId(resultSet.getLong("id"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			return user;
		}
	}
	
	public User createUser(User user) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", user.getEmail());
		parameters.put("password", user.getPassword());
		
		Number newId = jdbcInsert.executeAndReturnKey(parameters);
		user.setId(newId.longValue());
		return user;
	}
	
	public User getUser(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
	}
}
