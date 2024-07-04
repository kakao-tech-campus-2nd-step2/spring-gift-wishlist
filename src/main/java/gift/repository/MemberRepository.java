package gift.repository;

import gift.domain.Member;
import gift.domain.MemberRequest;
import gift.domain.Menu;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final RowMapper<MemberRequest> memberRowMapper = new RowMapper<MemberRequest>() {
        @Override
        public MemberRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new MemberRequest(
                    rs.getString("id"),
                    rs.getString("passwd")
            );
        }
    };


    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("members");
    }
    public void save(MemberRequest memberRequest) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(memberRequest);
        simpleJdbcInsert.execute(params);
    }


    public MemberRequest findById(String id) {
        String sql = "SELECT id, passwd FROM members WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, memberRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<MemberRequest> findAll() {
        String sql = "select id, passwd from members";
        List<MemberRequest> members = jdbcTemplate.query(
                sql,
                memberRowMapper);
        return members;
    }
}
