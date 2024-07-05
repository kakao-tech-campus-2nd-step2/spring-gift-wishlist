package gift.util;

import gift.domain.Member;
import gift.dto.JwtDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public JwtDto generateToken(Member member) {
        String token = Jwts.builder()
            .setSubject(member.getEmail())
            .claim("email", member.getEmail())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
        return new JwtDto(token);
    }
}
