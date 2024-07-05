package gift.Authorization;

import gift.Dto.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    // type 얻음
    public int getAuthentication(String token) {
        try {
            Claims claims = extractClaims(token);

            if (claims.get("type") == null) {
                throw new JwtException("error.invalid.token");
            }
            return claims.get("type", Integer.class);
        } catch (JwtException e) {
            throw new JwtException("error.invalid.token");
        }
    }

    public String generateToken(User user) {
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("email", user.getEmail())
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24 * 365)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        return accessToken;
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

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
