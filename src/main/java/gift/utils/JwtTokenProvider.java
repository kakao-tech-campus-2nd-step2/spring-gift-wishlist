package gift.utils;

import gift.auth.exception.InvalidAccessTokenException;
import gift.member.dto.MemberResDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(MemberResDto memberResDto, Date expiredAt) {
        Date now = new Date();
        return Jwts.builder()
                .subject(memberResDto.name())
                .claim("id", memberResDto.id())
                .claim("email", memberResDto.email())
                .claim("role", memberResDto.role())
                .issuedAt(now)
                .expiration(expiredAt)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public Long getMemberId(String accessToken) {
        return parseClaims(accessToken).get("id", Long.class);
    }

    private Claims parseClaims(String accessToken) {
        return (Claims) Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parse(accessToken)
                .getPayload();
    }

    public String getToken(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw InvalidAccessTokenException.EXCEPTION;
        }

        return header.substring(7);
    }
}