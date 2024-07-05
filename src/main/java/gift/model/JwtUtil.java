package gift.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public static String generateToken(User user) {
        return Jwts.builder()
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }
}