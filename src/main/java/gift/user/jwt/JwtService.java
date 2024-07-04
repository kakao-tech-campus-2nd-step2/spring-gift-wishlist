package gift.user.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final long expirationTime = 3600; // 1시간
    public static final String ISSUER = "KaKaoGiftServer";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_BEGIN_INDEX = 7;

    public String createToken(Long id, String role) {
        return TOKEN_PREFIX + Jwts.builder()
                .subject(id.toString())
                .issuer(ISSUER)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                .claim("role", role)
                .signWith(SECRET_KEY)
                .compact();
    }

    public Long getIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(removeBearerPrefix(token))
                .getPayload();
        return Long.parseLong(claims.getSubject());
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(removeBearerPrefix(token))
                .getPayload();
        return claims.get("role", String.class);
    }

    private String removeBearerPrefix(String token) {
        if (!token.startsWith(TOKEN_PREFIX)) {
            throw new SecurityException();
        }
        return token.substring(TOKEN_BEGIN_INDEX);
    }
}
