package gift.product.util;

import gift.product.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class CertifyUtil {

    private final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public Member encryption(String email, String password) {
        return new Member(Base64.getEncoder().encodeToString(email.getBytes()),
            Base64.getEncoder().encodeToString(password.getBytes()));
    }

    public String generateToken(Member member) {
        return Jwts.builder()
            .setSubject(member.getEmail())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
