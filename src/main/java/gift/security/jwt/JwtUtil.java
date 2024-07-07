package gift.security.jwt;

import gift.model.Member;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long tokenValidityInMilliseconds;

    public JwtUtil(@Value("${jwt.secret}") String secret,
        @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    public String generateToken(Member member) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);
        String credential = member.getEmail() +":"+ member.getPassword();
        return Jwts.builder()
            .setSubject(credential)
            .signWith(key, io.jsonwebtoken.SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT Token"+e);
            return false;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token"+e);
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}