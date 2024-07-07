package gift.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;


@Component
public class JwtTokenUtil {

    private final String secretKey;

    public JwtTokenUtil(@Value("${jwt.secret.key}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }
}