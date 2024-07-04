package gift.domain.repository;

import gift.domain.model.JoinRequestDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isExistEmail(String email) {
        return Boolean.TRUE.equals(
            jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM users WHERE email = ?)",
                Boolean.class, email));
    }

    public void save(JoinRequestDto joinRequestDto) {
        jdbcTemplate.update(
            "INSERT INTO users (email, password) VALUES (?, ?)",
            joinRequestDto.getEmail(),
            joinRequestDto.getPassword()
        );
    }
}
