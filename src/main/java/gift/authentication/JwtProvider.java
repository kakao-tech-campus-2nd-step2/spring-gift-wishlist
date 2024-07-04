package gift.authentication;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    @Value("${jwt.expiration}")
    private long expirationTime;

    public Token generateToken(String identifier) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiration = new Date(nowMillis + expirationTime);

        return Token.from(Jwts.builder()
            .subject(identifier)
            .issuer("gift")
            .issuedAt(now)
            .expiration(expiration)
            .signWith(key)
            .compact());
    }

}
