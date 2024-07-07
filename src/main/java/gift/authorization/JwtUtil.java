package gift.authorization;

import gift.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {


    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(User user) {
        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24 * 365));
        String accessToken = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("email", user.getEmail())
                .claim("type", user.getType())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        return accessToken;
    }
    
    //claims 추출
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    // type 얻음
    public String getUserType(String token) {
        try {
            Claims claims = extractClaims(token);
            if (claims.get("type") == null) {
                throw new JwtException("error.invalid.token.type.null");
            }
            return claims.get("type", String.class);
        } catch (JwtException e) {
            throw new JwtException("error.invalid.token");
        }
    }
    
    // email 얻음
    public String getUserEmail(String token) {
        try {
            Claims claims = extractClaims(token);
            System.out.println(token);
            if (claims.get("email") == null) {
                throw new JwtException("error.invalid.token.type.null");
            }
            return claims.get("email", String.class);
        } catch (JwtException e) {
            System.out.println("email null.. ");
            throw new JwtException("error.invalid.token");
        }
    }
    
    
    // 토큰 검사
    public Boolean ValidToken(String token, User user) {
        try {
            Claims claims = extractClaims(token);

            // Extracting email and expiration date from claims
            String email = claims.getSubject(); // Assuming email is stored as subject in JWT
            Date expiredDate = claims.getExpiration();

            // Validating token
            if (email != null && email.equals(user.getEmail()) && !expiredDate.before(new Date())) {
                System.out.println("Token is valid for user: " + user.getEmail());
                return true;
            } else {
                System.out.println("Token is either invalid or expired.");
                return false;
            }
        } catch (Exception e) {
            // Exception handling: If token parsing fails or any other exception occurs
            System.out.println("Failed to validate token: " + e.getMessage());
            return false;
        }
    }
}
