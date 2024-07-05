package gift.repository;

import gift.dto.UserRequestDto;
import gift.entity.User;
import gift.exception.UserAlreadyExistException;
import gift.mapper.UserMapper;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(UserRequestDto userRequest) {
        User user = UserMapper.toUser(userRequest);
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (!findByEmail(userRequest.email()).equals(null)) {
            throw new UserAlreadyExistException("이미 존재하는 Email입니다.");
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.email());
            ps.setString(2, user.password());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("email"),
            rs.getString("password")
        );
    }
}
