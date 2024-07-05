package gift.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private static final String signatureSecretKey = "c3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwtc3ByaW5nYm9vdC1qd3QtdHV0b3JpYWwK";

    private SecretKey key = Keys.hmacShaKeyFor(signatureSecretKey.getBytes(StandardCharsets.UTF_8));

    public String createJwt(Long userId){
        return Jwts.builder()
            .claim("userId", userId)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24L))
            .signWith(key)
            .compact();
    }

    public Long getUserId(String accessToken){
        Jws<Claims> jws;

        try {
            jws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(accessToken);
        } catch (JwtException e) {
            throw new RuntimeException(e);
        }

        // memberId 로 걸었던 클레임을 가져온다.
        return jws.getPayload()
            .get("memberId", Long.class);
    }

}
