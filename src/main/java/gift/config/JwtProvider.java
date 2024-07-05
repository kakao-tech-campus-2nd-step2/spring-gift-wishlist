package gift.config;

import static java.lang.System.currentTimeMillis;

import gift.domain.member.Member;
import gift.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtProvider {

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final Long EXPIRE = 1000L * 60 * 60 * 48;
    public static final String PREFIX = "Bearer ";

    public static String create(Member member) {
        return PREFIX + Jwts.builder()
            .subject(member.getId().toString())
            .claim("role", member.getRole())
            .expiration(new Date(currentTimeMillis() + EXPIRE))
            .signWith(SECRET_KEY)
            .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public static void verify(String jwt) {
        try {
            Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(jwt);
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }
    }

}
