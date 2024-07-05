package gift.domain;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtToken {

    @Value("${jwt.secret.key}")
    private  String secretKey;

    private final Long tokenExpTime = 3600L;

    public String createToken(User user) {
        return Jwts.builder()
            .setSubject(user.getEmail())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + tokenExpTime))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

    public String tokenToEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody()
            .getSubject();
    }
}
