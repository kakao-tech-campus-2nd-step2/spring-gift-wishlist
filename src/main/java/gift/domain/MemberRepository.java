package gift.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Member member) {
        String sql = "INSERT INTO member (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword());
    }

}
