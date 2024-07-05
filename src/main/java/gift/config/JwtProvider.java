package gift.config;

import static java.lang.System.currentTimeMillis;

import gift.domain.member.Member;
import gift.exception.InvalidTokenException;
import gift.exception.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtProvider {

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final Long EXPIRE = 1000L * 60 * 60 * 48;

    public static String create(Member member) {
        return Jwts.builder()
            .subject(member.getId().toString())
            .claim("role", member.getRole())
            .expiration(new Date(currentTimeMillis() + EXPIRE))
            .signWith(SECRET_KEY)
            .compact();
    }

    public static void verify(String jwt) {
        try {
            Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(jwt);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException();
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }
    }

}
