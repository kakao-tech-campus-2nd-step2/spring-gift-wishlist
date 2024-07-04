package gift;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

    public String extractEmail(String token) {
        Jws<Claims> claims = parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    public Boolean validateToken(String token, User user) {
        String email = extractEmail(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    private Jws<Claims> parseClaimsJws(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
    }

}
