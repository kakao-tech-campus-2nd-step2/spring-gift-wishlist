package gift.repository;

import gift.dto.UserEncryptedDTO;
import gift.dto.UserRequestDTO;
import gift.dto.UserResponseDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

    public UserResponseDTO create(UserEncryptedDTO user) {
        long id = insertWithGeneratedKey(user.email(), user.encryptedPW());

        return new UserResponseDTO(id, user.email());
    }

    private long insertWithGeneratedKey(String email, String password) {
        String insertSql = "insert into users(email, password) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, email);
            ps.setString(2, password);

            return ps;
        }, keyHolder);

        return (long) keyHolder.getKey();
    }
}
