package gift.repository;

import gift.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertMember(Member member) {
        var sql = "INSERT INTO member (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
    }

    public void updateMember(Member member) {
        var sql = "UPDATE member SET username=?, password=? WHERE id=?";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword(), member.getId());
    }

    public void deleteMember(Long id) {
        var sql = "DELETE FROM member WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Member getMember(Long id) {
        var sql = "SELECT * FROM member WHERE id=?";
        return jdbcTemplate.queryForObject(sql, memberRowMapper, id);
    }

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> new Member(
        rs.getLong("id"),
        rs.getString("email"),
        rs.getString("password")
    );

}
