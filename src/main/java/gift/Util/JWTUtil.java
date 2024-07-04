package gift.Util;

import gift.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


import javax.crypto.SecretKey;
import java.util.Date;

public class JWTUtil {

    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    /**
     * 토큰 생성
     */
    public static String generateToken(User user) {
        Claims claims = Jwts.claims();
        claims.put("username", user.getUserId());
        return createToken(claims, user.getUserId());
    }

    private static String createToken(Claims claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    /**
     * 토큰 유효여부 확인
     */
    public Boolean isValidToken(String token, User user) {
        //log.info("isValidToken token = {}", token);
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUserId()) && !isTokenExpired(token));
    }

    /**
     * 토큰의 Claim 디코딩
     */
    private Claims getAllClaims(String token) {
        //log.info("getAllClaims token = {}", token);
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Claim 에서 username 가져오기
     */
    public String getUsernameFromToken(String token) {
        String username = String.valueOf(getAllClaims(token).get("username"));
        //log.info("getUsernameFormToken subject = {}", username);
        return username;
    }

    /**
     * 토큰 만료기한 가져오기
     */
    public Date getExpirationDate(String token) {
        Claims claims = getAllClaims(token);
        return claims.getExpiration();
    }

    /**
     * 토큰이 만료되었는지
     */
    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }
}
