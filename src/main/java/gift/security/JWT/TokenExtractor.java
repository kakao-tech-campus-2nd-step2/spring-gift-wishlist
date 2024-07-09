package gift.security.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenExtractor {
    private final TokenProperies tokenProperies;

    public TokenExtractor(TokenProperies tokenProperies) {
        this.tokenProperies = tokenProperies;
    }

    public boolean validateToken(String token) {
        String userName = extractUsername(token);
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Date exp = extractExpiration(token);
        return exp.before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    private Claims extractClaims(String token) {
        Key key = tokenProperies.getKey();

        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}