package gift.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static final int EXPIRATION_TIME = 3600000;

    public static String generateToken(String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expMillis = nowMillis + EXPIRATION_TIME;
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
