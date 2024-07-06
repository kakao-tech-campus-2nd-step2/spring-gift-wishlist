package gift.dao;

import gift.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
    private final JdbcTemplate jdbcTemplate;

    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Member findByEmail(String email) {
        var sql = "SELECT * FROM member where email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper(), email);
        } catch (Exception e) {
            return null;
        }
    }

    public Member save(Member member) {
        var sql = "insert into member (email, password) values (?, ?)";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());

        return member;
    }

    private RowMapper<Member> userRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member(
                    rs.getLong("id"),
                    rs.getString("email"),
                    rs.getString("password")
            );
            return member;
        };
    }

}
