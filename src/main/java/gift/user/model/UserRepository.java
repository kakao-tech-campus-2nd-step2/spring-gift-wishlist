package gift.user.model;

import gift.user.model.dto.LoginRequest;
import gift.user.model.dto.SignUpRequest;
import gift.user.model.dto.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int signUpUser(SignUpRequest signUpRequest) {
        var sql = "INSERT INTO AppUser (email,password,role) VALUES (?, ?, ?)";
        Object[] params = new Object[]{signUpRequest.getEmail(), signUpRequest.getPassword(),
                signUpRequest.getRole()};
        return jdbcTemplate.update(sql, params);
    }

    public User checkUser(LoginRequest loginRequest) {
        var sql = "SELECT * FROM AppUser WHERE email = ? AND password = ? AND is_active = true";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> new User(
                            rs.getLong("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getBoolean("is_active")
                    ),
                    loginRequest.getEmail(), loginRequest.getPassword()
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // 결과가 없을 경우 null 반환
        }
    }

    public int deleteUser(Long id) {
        var sql = "UPDATE AppUser SET is_active = false WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}