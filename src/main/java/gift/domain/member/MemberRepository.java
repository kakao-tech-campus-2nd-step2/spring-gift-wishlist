package gift.domain.member;

import gift.domain.product.Product;
import gift.web.dto.MemberDto;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private JdbcTemplate jdbcTemplate;
    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> new Member(
            rs.getLong("id"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("role")
        );
    }

    public void insertMember(Member member) {
        var sql = "insert into members (email, password) values(?, ?)";
        jdbcTemplate.update(sql, member.email(), member.password());
    }

    public List<Member> findAll() {
        var sql = "select * from members";
        return jdbcTemplate.query(sql, memberRowMapper());
    }

    public Member getMemberByEmail(String email) {
        var sql = "select * from members where email = ?";
        return jdbcTemplate.queryForObject(sql, memberRowMapper(), email);
    }
}
