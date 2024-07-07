package gift.domain.member;

import gift.web.dto.MemberDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private JdbcTemplate jdbcTemplate;
    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertMember(MemberDto memberDto) {
        var sql = "insert into members (email, password) values(?, ?)";
        jdbcTemplate.update(sql, memberDto.email(), memberDto.password());
    }

    public MemberDto getMemberByEmail(String email) {
        var sql = "select * from members where email = ?";
        return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> new MemberDto(
                rs.getString("email"),
                rs.getString("password")
            ),
            email
        );
    }
}
