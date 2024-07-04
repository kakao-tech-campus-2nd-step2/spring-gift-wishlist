package gift;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
}
