package gift.user.model;

import gift.user.model.dto.SignUpRequest;
import gift.user.model.dto.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int signUpUser(SignUpRequest signUpRequest) {
        var sql = "INSERT INTO user (email,password,role) VALUES (?, ?, ?)";
        Object[] params = new Object[]{signUpRequest.getEmail(), signUpRequest.getPassword(),
                signUpRequest.getRole()};
        return jdbcTemplate.update(sql, params);
    }

    public User checkUser(String email, String password) {
        var sql = "SELECT * FROM user WHERE email = ? AND password = ? AND is_active = true";
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
                    email, password
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // 결과가 없을 경우 null 반환
        }
    }

    public int deleteUser(Long id) {
        var sql = "UPDATE user SET is_active = false WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}