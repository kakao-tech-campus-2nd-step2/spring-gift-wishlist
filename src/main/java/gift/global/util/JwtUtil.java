package gift.global.util;

import gift.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 * User를 기반으로 token을 생성 및 검증하는 유틸 클래스
 */
public class JwtUtil {

    private final static String secretKey;
    private final static Key key;

    static {
        secretKey = "MySecretKey01234;HelloWorld!#@#01234567890123456789"; // 256 비트(64글자) 이상의 길이어야 함
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public static String generateToken(User user) {
        return Jwts.builder()
            .setSubject(user.email())
            .claim("permission", user.permission())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1시간
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    private static Claims parseClaims(String token) {
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public static boolean isTokenValid(String token, User user) {
        Claims claims = parseClaims(token);
        String email = claims.getSubject();
        String permission = claims.get("permission", String.class);
        return email.equals(user.email()) && permission.equals(user.permission()) && !isTokenExpired(token);
    }

    public static boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }
}
