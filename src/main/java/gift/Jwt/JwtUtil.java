package gift.Jwt;

import gift.Dto.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("Email", user.getEmail())
                .claim("type", user.getType())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public int getAuthentication(String token) {
        try {
            Claims claims = parseClaims(token).getPayload();

            if (claims.get("type") != null) {
                throw new JwtException("error.invalid.token");
            }
            return claims.get("type", Integer.class);
        } catch (JwtException e) {
            throw new JwtException("error.invalid.token");
        }
    }

    private Jws<Claims> parseClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {
            return null; // Handle expired token case
        }
    }

}