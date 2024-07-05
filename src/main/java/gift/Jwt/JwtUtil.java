package gift.Jwt;

import gift.Dto.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.util.Date;

@Component
public class JwtUtil {

    String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .claim("Email", user.getEmail())
                .claim("type", user.getType())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    // type 얻음
    public int getAuthentication(String token) {
        try {
            Claims claims = parseClaims(token).getPayload();

            if (claims.get("type") == null) {
                throw new JwtException("error.invalid.token");
            }
            return claims.get("type", Integer.class);
        } catch (JwtException e) {
            throw new JwtException("error.invalid.token");
        }
    }

    // claims return
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


    // Token 유효기간
    public Boolean ValidToken(String token, User user) {
        Jws<Claims> claims = parseClaims(token);
        String email = claims.getPayload().get("email", String.class);
        Date expiredDate = claims.getPayload().getExpiration();
        return (email.equals(user.getEmail()) && !expiredDate.before(new Date()));
    }


}