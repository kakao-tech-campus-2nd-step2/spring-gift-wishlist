package gift.service;

import static java.lang.System.currentTimeMillis;

import gift.domain.Member;
import gift.error.exception.InvalidTokenException;
import gift.error.exception.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private final Long EXPIRE = 1000L * 60 * 60 * 48;

    public String create(Member member) {
        return Jwts.builder()
            .subject(member.getId().toString())
            .claim("role", member.getRole())
            .expiration(new Date(currentTimeMillis() + EXPIRE))
            .signWith(SECRET_KEY)
            .compact();
    }

    public void verify(String jwt) {
        try {
            Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(jwt);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException();
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }
    }

}
