package gift.service.auth;

import gift.model.Member;
import gift.model.MemberRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {

    @Value("${SECRET_KEY}")
    private String secretKey;
    @Value("${EXPIRED_TIME}")
    private long expiredTime;

    public AuthService() {
    }

    public String createAccessTokenWithMember(Member member) {
        var token = Jwts.builder()
                .subject(member.getId().toString())
                .claim("name", member.getName())
                .claim("role", member.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(getSecretKey())
                .compact();
        return token;
    }

    public Long getMemberIdWithToken(String token) {
        var id = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.parseLong(id);
    }

    public MemberRole getMemberRoleWithToken(String token) {
        var role = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role");
        return MemberRole.valueOf(role.toString());
    }

    public String getTokenWithAuthorizationHeader(String authorizationHeader) {
        var header = authorizationHeader.split(" ");
        if (header.length != 2) throw new IllegalArgumentException("잘못된 헤더 정보입니다.");
        return header[1];
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
