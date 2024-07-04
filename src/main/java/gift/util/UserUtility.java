package gift.util;

import gift.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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

    public static Object stringToObject(String accessToken) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("accessToken", accessToken);
        return obj;
    }
}
