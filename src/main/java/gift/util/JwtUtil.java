package gift.util;

import gift.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public static String generateJwtToken(Member member) {
        String accessToken = Jwts.builder()
            .setSubject(member.getId().toString())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();

        return accessToken;
    }
}
