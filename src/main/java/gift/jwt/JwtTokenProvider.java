package gift.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String secretKey = "asdsadaaaaaaaaaaaaaaaaaaaaaadfsfdwefafaefweafwsg";
    private final long validityInMilliseconds = 60 * 60 * 1000;  // 1시간

    public String createToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
            .setSubject(email)  // 식별하는데 사용되는 값
            .setIssuedAt(now)   // 토큰 발행 시간
            .setExpiration(validity) // 토큰 만료 시간
            .claim("email", email)  // 클레임 설정
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String extractSubject(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .getSubject();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
