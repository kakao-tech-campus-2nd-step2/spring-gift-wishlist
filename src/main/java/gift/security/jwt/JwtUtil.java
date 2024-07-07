package gift.security.jwt;

import gift.model.Member;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    
    private final SecretKey key;
    private final JwtParser jwtParser;
    private final long tokenValidityInMilliseconds;

    public JwtUtil(@Value("${jwt.secret}") String secret,
        @Value("${jwt.token-validity-in-seconds:86400}") long tokenValidityInSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtParser = Jwts.parser().decryptWith(key).build();
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    public String generateToken(Member member) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        String credentials = member.getEmail() + ":" + member.getPassword();
        return Jwts.builder().subject(credentials)
            .signWith(key).expiration(validity)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJwt(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = jwtParser.parseClaimsJwt(token).getPayload();
        return claims.get("email", String.class);
    }
}