package gift.repository;

import gift.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void signUp(Member member) {
        String sql = "INSERT INTO members(email, password) values (?, ?)";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
    }

    public Member signIn(Member member) {
        String sql = "SELECT email,password FROM members WHERE email=? and password=?";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) ->
            new Member(
                resultSet.getString("email"),
                resultSet.getString("password")
            ), member.getEmail(), member.getPassword()
        );
    }
}
