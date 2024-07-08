package gift.repository;


import gift.domain.TokenAuth;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository

public class TokenJDBCRepository implements TokenRepository {

    private final JdbcTemplate jdbcTemplate;

    public TokenJDBCRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String save(String token, String email) {
        String sql = "INSERT INTO tokenauth(token, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, token, email);
        return token;
    }

    public Optional<TokenAuth> findTokenByToken(String token){
        String sql = "SELECT token, email FROM tokenauth WHERE token = ?";
        List<TokenAuth> tokenAuths = jdbcTemplate.query(sql, new Object[]{token}, (rs, rowNum) -> new TokenAuth(
                rs.getString("token"),
                rs.getString("email")
        ));

        if (tokenAuths.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(tokenAuths.getFirst());
    }

}

