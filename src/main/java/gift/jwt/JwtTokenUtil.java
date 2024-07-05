package gift.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import io.jsonwebtoken.Jwts;


public class JwtTokenUtil {

    private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public static Claims parseToken(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public static String getEmailFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }
}