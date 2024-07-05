package gift.repository;

import gift.dto.Token;
import jakarta.annotation.PostConstruct;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TokenRepository {
    private final JdbcTemplate jdbcTemplate;

    public TokenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initialize() {
        createTokenTable();
    }

    private void createTokenTable() {
        var sql = """
                create table token (
                  userId bigint,
                  tokenValue varchar(255),
                  primary key(userId)
                )
                """;
        jdbcTemplate.execute(sql);
    }
    public Token getTokenByUserId(Long userId) {
        String sql = "select * from token where userId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new TokenRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;  // 결과가 없을 경우 null 반환
        }
    }

    public void saveToken(Token createdToken) {

        String sql = "INSERT INTO token (userId, tokenValue) VALUES (?, ?)";
        jdbcTemplate.update(sql, createdToken.getUserId(), createdToken.getValue());
    }


    private static class TokenRowMapper implements RowMapper<Token> {
        @Override
        public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
            Token token = new Token();
            token.setUserId(rs.getLong("userId"));
            token.setValue(rs.getString("tokenValue"));
            return token;
        }
    }

}
