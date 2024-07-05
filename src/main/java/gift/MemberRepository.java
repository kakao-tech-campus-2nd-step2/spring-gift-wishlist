package gift;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(@NonNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Member.MemberBuilder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
        }
    }

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", new MemberRowMapper());
    }

    public Member findByEmail(String email) {
        return jdbcTemplate.query(
            "SELECT * FROM member WHERE email = ?",
            new Object[]{email},
            resultSet -> {
                if (resultSet.next()) {
                    return new Member.MemberBuilder()
                        .id(resultSet.getLong("id"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
                }
                return null;
            }
        );
    }

    public Member save(@NonNull Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            connection -> {
                PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO member (email, password) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, member.getEmail());
                ps.setString(2, member.getPassword());
                return ps;
            },
            keyHolder
        );
        long id = keyHolder.getKey().longValue();
        return new Member(id, member.getEmail(), member.getPassword());
    }

    public void deleteByEmail(String email) {
        jdbcTemplate.update("DELETE FROM member WHERE email = ?", email);
    }
}
