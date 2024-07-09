package gift.web.jwt;

import gift.web.dto.MemberDto;
import gift.web.dto.Token;
import gift.web.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtUtils {
    private static final long exp_time = 15 * 60 * 1000;
    private static final String secretKey = "6a6115fd7149b725c2ce38080aa88276d9113e949692aa68ed9fc36259f3f268";

    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public static String createJWT(MemberDto memberDto) {
        return Jwts.builder()
            .claim("email", memberDto.email())
            .expiration(new Date(System.currentTimeMillis() + exp_time))
            .signWith(getSigningKey())
            .compact();
    }

    public static Claims validateAndGetClaims(Token token) {
        try {
            return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(
                    token.token()
                )
                .getPayload();
        } catch (Exception e) {
           throw new UnauthorizedException("토큰이 유효하지 않음");
        }
    }
}