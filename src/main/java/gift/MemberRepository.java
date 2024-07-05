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
}
