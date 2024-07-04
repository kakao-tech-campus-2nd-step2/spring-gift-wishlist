package gift.member.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .compact();
    }

}
