package gift.global.util;

import gift.domain.user.User;
import gift.domain.user.exception.UserTokenNotExistsException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User를 기반으로 token을 생성 및 검증하는 유틸 클래스
 */
@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;
    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(User user) {
        return Jwts.builder()
            .setSubject(user.email())
            .claim("permission", user.permission())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1시간
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    private Claims parseClaims(String token) {
        if (token == null) {
            throw new UserTokenNotExistsException();
        }

        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public String getPermission(String token) {
        return parseClaims(token).get("permission", String.class);
    }
}
