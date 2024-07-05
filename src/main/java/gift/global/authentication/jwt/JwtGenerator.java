package gift.global.authentication.jwt;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    private final JwtValidator jwtValidator;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;  // 1일
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14;  // 2주

    public JwtGenerator(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    public JwtToken createToken(Long id) {
        long current = System.currentTimeMillis();
        var accessExpireTime = new java.util.Date(current + ACCESS_TOKEN_EXPIRE_TIME);
        var refreshExpireTime = new java.util.Date(current + REFRESH_TOKEN_EXPIRE_TIME);
        var accessToken = generateToken(id, accessExpireTime, java.util.Map.of("type", TokenType.ACCESS));
        var refreshToken = generateToken(id, refreshExpireTime, java.util.Map.of("type", TokenType.REFRESH));
        return new JwtToken(accessToken, refreshToken);
    }

    private String generateToken(Long id, Date expireTime, Map<String, Object> claims) {
        SecretKey secretKey = jwtValidator.secretKey();

        return Jwts.builder()
            .claims(claims)
            .subject(id.toString())
            .expiration(expireTime)
            .signWith(secretKey)
            .compact();
    }


}
