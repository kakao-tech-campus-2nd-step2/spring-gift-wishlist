package gift.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JwtToken {

    private String secretKey = Base64.getEncoder().encodeToString("kakao Tech campus backend".getBytes());;
    private final long tokenExpTime = 3600L; // 1시간

    public JwtToken() {
    }

    public Token createToken(Login login) {
        Claims claims = Jwts.claims();
        claims.put("email", login.getEmail());

        ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime expirationDateTime = now.plusSeconds(tokenExpTime);

        return new Token(Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(now.toInstant()))
            .setExpiration(Date.from(expirationDateTime.toInstant()))
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact());
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token", e);
        }
    }

    public String getEmail(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token).getBody();
        return claims.get("email",String.class);
    }
}
