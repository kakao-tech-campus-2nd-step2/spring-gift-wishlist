package gift.domain.user;

import gift.domain.user.dto.UserPermissionChangeRequestDto;
import gift.domain.user.dto.UserRequestDto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> getRowMapper() {
        return (resultSet, rowNum) -> new User(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getString("permission")
        );
    }

    public Optional<User> findByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, getRowMapper(), email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<User> updatePassword(UserRequestDto requestDto) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        jdbcTemplate.update(sql, requestDto.password(), requestDto.email());
        return findByEmail(requestDto.email());
    }

    public Optional<User> updatePermission(UserPermissionChangeRequestDto requestDto) {
        String sql = "UPDATE users SET permission = ? WHERE email = ?";
        jdbcTemplate.update(sql, requestDto.permission(), requestDto.email());
        return findByEmail(requestDto.email());
    }

    public User save(UserRequestDto requestDto) {
        String sql = "INSERT INTO users (email, password, permission) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, requestDto.email(), requestDto.password(), "user");
        return findByEmail(requestDto.email()).orElseThrow(RuntimeException::new);
    }

    public void deleteByEmail(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        jdbcTemplate.update(sql, email);
    }
}
