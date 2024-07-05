package gift.user.jwt;

import static gift.user.jwt.JwtUtil.ISSUER;
import static gift.user.jwt.JwtUtil.SECRET_KEY;
import static gift.user.jwt.JwtUtil.TOKEN_BEGIN_INDEX;
import static gift.user.jwt.JwtUtil.TOKEN_PREFIX;
import static gift.user.jwt.JwtUtil.expirationTime;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtService {


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
