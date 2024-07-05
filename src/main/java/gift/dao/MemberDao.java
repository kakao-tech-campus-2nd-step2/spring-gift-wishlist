package gift.dao;

import gift.model.member.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertMemeber(Member member) {
        var sql = "insert into members (email, password, role) values (?, ?, ?)";
        jdbcTemplate.update(sql, member.email(), member.password(),member.role());
    }
}
