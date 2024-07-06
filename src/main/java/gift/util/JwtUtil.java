package gift.util;

import gift.domain.Member;
import gift.dto.JwtDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public JwtDto generateToken(Member member) {
        String token = Jwts.builder()
            .subject(member.getId().toString())
            .claim("email", member.getEmail())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
        return new JwtDto(token);
    }

    public boolean validateToken(JwtDto token){
        try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token.getToken());
            return true;
        } catch (SignatureException | ExpiredJwtException e) {
            return false;
        }
    }

    private Claims getClaims(JwtDto token) {
        try {
            return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token.getToken())
                .getPayload();
        } catch (Exception e) {
            // 유효하지 않은 토큰
            return null;
        }
    }

    public String getEmailFromToken(JwtDto token) {
        Claims claims = getClaims(token);
        if( claims != null){
            return claims.get("email", String.class);
        }
        return null;
    }

    public String getSubjectFromToken(JwtDto token) {
        Claims claims = getClaims(token);
        if( claims != null){
            return claims.getSubject();
        }
        return null;
    }
}
