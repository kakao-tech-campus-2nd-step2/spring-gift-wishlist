package gift.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtTokenUtil {
    @Value("${jwt.access-token-validity}")
    private Long accessTokenValidity;

    @Value("${jwt.refresh-token-validity}")
    private Long refreshTokenValidity;

    private final Set<String> tokenBlacklist = new HashSet<>();

    public String generateAccessToken(String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // HS512에 대한 안전한 키 생성
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity * 1000))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // HS512에 대한 안전한 키 생성
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity * 1000))
                .signWith(key)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // HS512에 대한 안전한 키 생성
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        if (tokenBlacklist.contains(token)) {
            return false;
        }
        try {
            final Claims claims = getClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public void blacklistToken(String token) {
        tokenBlacklist.add(token);
    }
}
