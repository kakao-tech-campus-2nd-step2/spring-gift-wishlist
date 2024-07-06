package gift.util;

import gift.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

public class UserUtility {
    private final static String SECRET_KEY = Vars.secretKey;

    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String makeAccessToken(User user) {
        String accessToken = Jwts.builder()
                .claim("email", user.getEmail())
                .signWith(getSecretKey())
                .compact();
        return accessToken;
    }

    public static Object accessTokenToObject(String accessToken) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("accessToken", accessToken);
        return obj;
    }

    public static Object emailToObject(String email) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("email", email);
        return obj;
    }

    public static Claims tokenParser(String accessToken) {
        try {
            Jws<Claims> jwt;
            jwt = Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(accessToken);
            return jwt.getBody();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}
