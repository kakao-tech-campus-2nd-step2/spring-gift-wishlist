package gift.repository;

import gift.exception.LoginErrorException;
import gift.exception.MemberException;
import gift.model.Member;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Member insertMember(Member member) {
        try {
            var sql = "INSERT INTO member (email, password) VALUES (?, ?)";
            jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
            return getMemberByEmail(member.getEmail());
        } catch (DuplicateKeyException e) {
            throw new MemberException(e);
        }
    }

    public Member getMemberByEmail(String email) {
        try {
            var sql = "SELECT * FROM member WHERE email=?";
            return jdbcTemplate.queryForObject(sql, memberRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            throw new LoginErrorException(e);
        }

    }

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> new Member(
        rs.getLong("id"),
        rs.getString("email"),
        rs.getString("password")
    );

}
