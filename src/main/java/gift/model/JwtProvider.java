package gift.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

public class JwtProvider {
    @Value("${jwt.key}")
    private String secretKey;

    public String generateToken(User user) {
        return Jwts.builder()
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }
}