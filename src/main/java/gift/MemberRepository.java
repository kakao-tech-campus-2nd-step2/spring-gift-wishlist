package gift;

import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.Map;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public MemberRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("members")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Member> MemberRowMapper = (Resultset, rowNum) -> {
        Member member = new Member();
        member.setId(Resultset.getLong("id"));
        member.setEmail(Resultset.getString("email"));
        member.setPassword(Resultset.getString("password"));
        return member;
    };

    public Member findMemberByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM members WHERE email = ?",
                    MemberRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveMember(@Valid Member member) {
        Map<String, Object> sm = Map.of(
        "email", member.getEmail(),
        "password", member.getPassword()
        );
        Number newId = simpleJdbcInsert.executeAndReturnKey(sm);
        member.setId(newId.longValue());
    }
}
