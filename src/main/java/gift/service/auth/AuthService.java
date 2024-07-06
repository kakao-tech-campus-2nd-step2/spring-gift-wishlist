package gift.service.auth;

import gift.model.Member;
import gift.utils.AuthUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
                .signWith(AuthUtils.getSecretKey(secretKey))
                .compact();
        return token;
    }
}
