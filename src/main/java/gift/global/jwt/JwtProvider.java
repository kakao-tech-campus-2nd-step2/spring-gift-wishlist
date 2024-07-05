package gift.global.jwt;

import gift.domain.user.User;
import gift.domain.user.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtProvider {

    private static final String SECRET_KEY = "katecamtesttesttesttesttesttesttesttesttesttesttest";
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    /**
     * JWT 토큰 생성
     */
    public static String generateToken(User user) {
        return Jwts.builder()
            .claim("email", user.getEmail())
            .claim("id", user.getId())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    /**
     * 토큰에서 현재 로그인한 사용자 정보 추출
     * */
    public UserInfo getClaim(String token) {
        Claims claimsBody = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(removeBearer(token))
            .getBody();

        UserInfo currentUser = UserInfo.builder()
            .email(claimsBody.get("email").toString())
            .id((claimsBody.get("id") instanceof Integer) ? Long.valueOf((Integer) claimsBody.get("id")) : (Long) claimsBody.get("id"))
            .build();

        return currentUser;
    }

    private String removeBearer(String token) {
        return token.replace("Bearer", "").trim();
    }
}
