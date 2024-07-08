package gift.authService;

import gift.model.Login;
import gift.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

/**
 * JWT 토큰을 생성하고 검증하는 클래스 JWT 토큰은 Base64로 인코딩된 secretKey를 사용하여 생성하고 검증한다.
 */
public class JwtToken {

    private String secretKey = Base64.getEncoder()
        .encodeToString("kaTeCamABCDEFGHIJKLmnoPQRSTUVWXYZ".getBytes());
    private final long tokenExpTime = 3600L; // 1시간

    public JwtToken() {
    }

    /**
     * JWT 토큰 생성
     *
     * @param login 로그인 정보
     * @return 생성된 토큰
     */
    public Token createToken(Login login) {
        Claims claims = Jwts.claims();
        claims.put("email", login.getEmail());
        claims.put("id", login.getId());

        ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime expirationDateTime = now.plusSeconds(tokenExpTime);

        return new Token(Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(now.toInstant()))
            .setExpiration(Date.from(expirationDateTime.toInstant()))
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact());
    }

    /**
     * JWT 토큰 검증
     *
     * @param token 검증할 토큰
     * @return 검증된 토큰
     */
    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token", e);
        }
    }

    /**
     * JWT 토큰에서 email 추출
     *
     * @param token 추출할 토큰
     * @return 추출된 email
     */
    public String getEmail(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build()
            .parseClaimsJws(token).getBody();
        return claims.get("email", String.class);
    }

    /**
     * JWT 토큰에서 id 추출
     *
     * @param token 추출할 토큰
     * @return 추출된 id
     */
    public Long getId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build()
            .parseClaimsJws(token).getBody();
        return claims.get("id", Long.class);
    }
}