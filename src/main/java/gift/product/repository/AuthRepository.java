package gift.product.repository;

import gift.product.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsByEmail(String email) {
        var sql = "SELECT * FROM Member WHERE email = ?";
        boolean isMemberNotExist = jdbcTemplate.query(sql, (rs, rowNum) -> 0, email).isEmpty();

        return !isMemberNotExist;
    }

    public void registerMember(Member member) {
        var sql = "INSERT INTO Member (email, password) VALUES (?, ?)";

        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
    }
}
