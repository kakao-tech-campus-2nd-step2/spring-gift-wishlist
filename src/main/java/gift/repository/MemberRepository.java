package gift.repository;



import gift.model.Member;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createMemberTable();
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("members")
            .usingGeneratedKeyColumns("id");
        createMemberTable();
    }

    public void createMemberTable() {
        String sql = "CREATE TABLE IF NOT EXISTS members (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
            "email VARCHAR(255) NOT NULL," +
            "password VARCHAR(255) NOT NULL," +
            "name VARCHAR(255)," +
            "role VARCHAR(50)" +
            ")";
        jdbcTemplate.execute(sql);
    }

    public Member registerMember(Member member) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", member.getEmail());
        parameters.put("password", member.getPassword());
        parameters.put("name", member.getName());
        parameters.put("role", member.getRole());
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        member.setId(newId.longValue());
        return member;
    }
