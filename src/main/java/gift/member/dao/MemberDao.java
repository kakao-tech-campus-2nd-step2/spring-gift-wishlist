package gift.member.dao;

import gift.member.domain.Member;
import gift.error.DuplicateEmailException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member save(Member member) {
        String sql = "INSERT INTO users (email, password) VALUES(?, ?)";

        try {
            jdbcTemplate.update(sql, member.email(), member.password());
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateEmailException(member.email() + "은(는) 이미 존재하고 있는 이메일입니다.");
        }

        return member;
    }

    public Optional<Member> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        return jdbcTemplate.query(sql,
                        (rs, rowNum) -> new Member(
                                rs.getLong("id"),
                                rs.getString("email"),
                                rs.getString("password")
                        ),
                        email
                )
                .stream()
                .findFirst();
    }

}
