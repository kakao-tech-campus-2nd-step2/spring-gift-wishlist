package gift;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private final SecretKey key = SIG.HS256.key().build();

    public String generateToken(Long userId, UserRole role) {

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        return Jwts.builder()
            .subject(String.valueOf(userId))
            .claim("role", role)
            .expiration(expiredDate)
            .signWith(key)
            .compact();
    }



}
