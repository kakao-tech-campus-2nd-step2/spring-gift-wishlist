package gift.Token;

import gift.Model.Role;
import gift.Model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public String createToken(UserInfo userInfo) {
        return Jwts.builder()
                .setSubject(userInfo.email())
                .claim("role", userInfo.role())
                .setIssuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public String getEmailFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.get("email", String.class);
            return email;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
            return null;
        }
    }

    public Role getRoleFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build().parseSignedClaims(token).getPayload();

            Role role = claims.get("role", Role.class);
            return role;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
            return null;
        }
    }
}
