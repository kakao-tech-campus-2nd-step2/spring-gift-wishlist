package gift.util;

import gift.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class UserUtility {
    public static String makeAccessToken(User user) {
        String accessToken = Jwts.builder()
                .claim("email", user.getEmail())
                .signWith(Keys.hmacShaKeyFor(Vars.secretKey.getBytes()))
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

    public static String tokenParser(String accessToken) {
        try {
            Jws<Claims> jwt;
            jwt = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(Vars.secretKey.getBytes()))
                    .build()
                    .parseSignedClaims(accessToken);
            Claims claims = jwt.getBody();
            String email = claims.get("email", String.class);
            return email;
        } catch (JwtException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
