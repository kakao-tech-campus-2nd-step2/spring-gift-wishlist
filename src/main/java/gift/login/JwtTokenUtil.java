package gift.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 토큰 만료 시간 설정 (예: 1시간 후)
        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(key)
            .compact();
    }

    public Claims decodeJwt(String token) {
            return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
