package Member;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Base64;

@Repository
public class MemberDAO {
    private final JdbcTemplate jdbcTemplate;

    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create() {
        var sql = """
                create table member (
                    email varchar(255),
                    password varchar(255),
                    token varchar(255)
                )
                """;
        jdbcTemplate.execute(sql);
    }


    public String insert(Member member) throws Exception {
        String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

        String accessToken = Jwts.builder()
                .setSubject(member.email())
                .claim("email", member.email())
                .claim("password", member.password())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        String encodedEmail = Base64.getEncoder().encodeToString(member.email().getBytes());
        String encodedPassword = Base64.getEncoder().encodeToString(member.password().getBytes());

        var sql = "INSERT INTO member (email, password, token) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, encodedEmail, encodedPassword, accessToken);
        return accessToken;
    }

    public String getToken(Member member) throws Exception{
        var sql = "SELECT token FROM member WHERE email = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, String.class, member.email(), member.password());
    }

    public boolean validateToken(String token) throws HttpClientErrorException.Unauthorized {
        var sql = "SELECT * FROM member WHERE token = ?";
        var result = jdbcTemplate.queryForObject(sql, token);
        return result == null;
    }
}
