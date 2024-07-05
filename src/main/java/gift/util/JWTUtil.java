package gift.util;

import gift.exception.member.ExpiredTokenException;
import gift.exception.member.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private static final String SECRET_KEY = "N0xtMDdMbTA3SmlrSU8yRmpPMkJyQ0RzdXFEdGpienNpcVFnTXVxNHNDQjhJT3kycWV1Q3FPdU1nQ0JDUlNEc25iVHFzcjNydVlnZ09paz0=";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private static final long EXPIRATION_TIME = 30 * 60 * 1000;

    public static String generateToken(Long memberId, String email) {
        return Jwts.builder()
            .setSubject(email)
            .claim("memberId", memberId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(KEY, SignatureAlgorithm.HS512)
            .compact();
    }

    public static Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("토큰이 만료되었습니다."); //, e);
        } catch (JwtException e) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다."); //, e);
        }
    }
}
