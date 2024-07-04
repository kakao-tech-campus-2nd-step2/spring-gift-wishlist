package gift.util;

import gift.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class AuthServiceUtils {

    @Value("${SECRET_KEY}")
    private String secretKeyProperty;
    private static String secretKey;

    public AuthServiceUtils() {
    }

    @PostConstruct
    private void init() {
        this.secretKey = this.secretKeyProperty;
    }

    public static String createAccessTokenWithMember(Member member) {
        var token = Jwts.builder()
                .subject(member.getId().toString())
                .claim("name", member.getName())
                .claim("role", member.getRole())
                .signWith(getSecretKey())
                .compact();
        return token;
    }

    public static Long getMemberIdWithToken(String token) {
        var id = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.parseLong(id);
    }

    public static Claims getClaimsWithToken(String token) {
        var claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token).getPayload();
        return claims;
    }

    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
