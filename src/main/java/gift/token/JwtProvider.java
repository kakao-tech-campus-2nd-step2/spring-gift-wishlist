package gift.token;

import gift.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final SecretKey key;
    private static final String PREFIX = "Bearer ";

    public JwtProvider(@Value("${key.jwt.secret-key}") String secretKey) {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Member member) {
        return PREFIX + Jwts.builder()
            .claim("email", member.email())
            .claim("password", member.password())
            .signWith(key)
            .compact();
    }

    public Member getMemberFromToken(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token.replace(PREFIX, ""))
            .getPayload();
        return new Member(
            claims.get("email", String.class),
            claims.get("password", String.class)
        );
    }
}
