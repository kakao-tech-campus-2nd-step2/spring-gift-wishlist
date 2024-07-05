package gift.service;

import gift.vo.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Token 만료 시간
    private final long TOKEN_TIME = 60 * 60 *1000L; //60분

    private final static String SECRET_KEY = "mysecretmysecretmysecretmysecretmysecretmysecret";
    private final static SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     *
     * @param username: Username
     * @param role: 권한
     * @return JWT 토큰 생성 후 반환
     */
    public String generateToken(String username, MemberRole role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setExpiration(new Date(new Date().getTime() + TOKEN_TIME))
                .signWith(key)
                .compact();
    }

    /**
     * Token에서 Member: email 추출
     * @param token JWT 토큰
     * @return Member의 이메일 String
     */
    public String getMemberEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Token에서 Claim 추출
     * @param token JWT 토큰
     * @return Claims
     */
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
