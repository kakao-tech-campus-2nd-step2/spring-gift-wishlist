package gift.user.model;

import gift.product.model.dto.UpdateProductRequest;
import gift.user.model.dto.SignUpRequest;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
@Repository
class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int signUpUser(SignUpRequest signUpRequest) {
        var sql = "INSERT INTO user (name, email, role) VALUES (?, ?, ?)";
        Object[] params = new Object[]{signUpRequest.getEmail(), signUpRequest.getPassword(),
                signUpRequest.getRole()};
        return jdbcTemplate.update(sql, params);
    }

    public int deleteUser(Long id) {
        var sql = "UPDATE user SET is_active = false WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}