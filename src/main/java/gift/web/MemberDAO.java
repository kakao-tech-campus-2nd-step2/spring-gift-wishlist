package gift.web;

import gift.web.dto.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
    private JdbcTemplate jdbcTemplate;
    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertMember(Member member) {
        var sql = "insert into members (email, password) values(?, ?)";
        jdbcTemplate.update(sql, member.email(), member.password());
    }

    public Member getMemberByEmail(String email) {
        var sql = "select * from members where email = ?";
        return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> new Member(
                rs.getString("email"),
                rs.getString("password")
            ),
            email
        );
    }
}
