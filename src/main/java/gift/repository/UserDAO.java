package gift.repository;

import gift.dto.UserResponseDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserResponseDTO> findAll() {
        String sql = "select * from users";

        return jdbcTemplate.query(sql, (record, rowNum) -> new UserResponseDTO(
                    record.getLong("id"),
                    record.getString("email")
            )
        );
    }

}
