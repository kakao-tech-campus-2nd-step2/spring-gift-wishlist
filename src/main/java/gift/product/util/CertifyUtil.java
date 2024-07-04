package gift.product.util;

import gift.product.exception.UnauthorizedException;
import gift.product.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class CertifyUtil {

    private final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public Member encryption(String email, String password) {
        return new Member(Base64.getEncoder().encodeToString(email.getBytes()),
            Base64.getEncoder().encodeToString(password.getBytes()), 0);
    }

    public String generateToken(Member member) {
        return Jwts.builder()
            .setSubject(member.getEmail())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

    public Claims extractClaims(String token) {
        System.out.println("[CertifyUtil] extractClaims");
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public void validateToken(String token) {
        System.out.println("[CertifyUtil] validateToken()");
        try {
            extractClaims(token);
            System.out.println("토큰으로 이메일 추출");
        } catch (Exception e) {
            System.out.println("도중 에러 발생");
            e.printStackTrace();
            throw new UnauthorizedException("Invalid token");
        }
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        System.out.println("[CertifyUtil] getTokenFormRequest()");
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header must be provided");
        }
        return authorizationHeader.substring(7);
    }

}
