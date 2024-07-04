package member.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "zkeidlqcnguitoejkadnfmdf";

    private static final long EXPIRATION_TIME = 3600000;

    public String generateToken(String username) {
        // 현재 시간
        long nowMillis = System.currentTimeMillis();
        // 만료 시간
        Date expirationDate = new Date(nowMillis + EXPIRATION_TIME);

        String token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(nowMillis))
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();

        return token;
    }
}
