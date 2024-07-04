package gift.repository;

import gift.domain.member.Member;
import gift.domain.member.MemberRole;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Optional<Member> findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM member WHERE email = ? AND password = ?";

        try {
            Member member = jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> Member.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .role(MemberRole.valueOf(rs.getString("role")))
                    .build(),
                email,
                password
            );
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
