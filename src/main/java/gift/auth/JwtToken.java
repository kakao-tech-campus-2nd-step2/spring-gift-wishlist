package gift.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtToken {

    private String secretKey;
    private long tokenExpTime;

    public JwtToken(@Value("${jwt.secretKey}") String secretKey,
        @Value("${jwt.tokenExpTime:3600}") long tokenExpTime) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.tokenExpTime = tokenExpTime;
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

    public String getEmail(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build()
            .parseClaimsJws(token).getBody();
        return claims.get("email", String.class);
    }
}
