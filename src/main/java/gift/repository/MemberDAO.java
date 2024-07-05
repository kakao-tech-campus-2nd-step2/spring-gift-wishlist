package gift.repository;

import gift.dto.MemberDTO;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public MemberDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("MEMBER");
    }

    public MemberDTO findMember(String email) {
        var sql = "SELECT * FROM MEMBER WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, memberRowMapper(), email);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    private RowMapper<MemberDTO> memberRowMapper() {
        return (resultSet, rowNum) -> new MemberDTO(
            resultSet.getString("email"),
            resultSet.getString("password"));
    }
}
