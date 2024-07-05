package gift.service;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import gift.exception.UnauthorizedException;
import gift.exception.UserNotFoundException;
import gift.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

@Service
public class AuthService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcInsert jdbcInsert;
	
	private String secret = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
	private SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
	
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
	
	public User getUser(String token) {
        try {
            String email = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token.replace("Bearer ", ""))
                    .getPayload()
                    .get("email", String.class);

            return searchUser(email);
        } catch(Exception e) {
            throw new UnauthorizedException("Invalid or expired token");
        }
    }
	
	public User searchUser(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
        	throw new UserNotFoundException("User with email " + email + " not found");
        }
	}
	
	public String grantAccessToken(User user) {
		return Jwts.builder()
		    .setSubject(user.getId().toString())
		    .claim("email", user.getEmail())
		    .signWith(secretKey, SignatureAlgorithm.HS256)
		    .compact();
	}
}
