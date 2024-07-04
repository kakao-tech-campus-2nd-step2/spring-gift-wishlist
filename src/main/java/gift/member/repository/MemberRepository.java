package gift.member.repository;

import gift.member.model.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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

    public Member findByEmail(String email) {
        String userEmail = "SELECT * FROM members WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(userEmail, new Object[]{email}, new MemberRowMapper());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(Member member) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", member.getEmail());
        parameters.put("password", member.getPassword());

        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        member.setId(newId.longValue());
    }

    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setEmail(rs.getString("email"));
            member.setPassword(rs.getString("password"));
            return member;
        }
    }
}
