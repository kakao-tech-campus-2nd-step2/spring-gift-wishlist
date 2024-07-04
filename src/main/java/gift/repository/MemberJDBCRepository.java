package gift.repository;

import gift.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MemberJDBCRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public MemberJDBCRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
    }

    public Member save(Member member) {
        var id = insertAndReturnId(member);
        return createMemberWithId(id, member);
    }

    public boolean existsByEmail(String email) {
        var sql = "SELECT COUNT(*) FROM member WHERE email = ?";
        var count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        if (count > 0) return true;
        return false;
    }

    private Long insertAndReturnId(Member member) {
        var param = new BeanPropertySqlParameterSource(member);
        return jdbcInsert.executeAndReturnKey(param).longValue();
    }

    private Member createMemberWithId(Long id, Member member) {
        return new Member(id, member.getName(), member.getEmail(), member.getPassword(), member.getRole());
    }
}
