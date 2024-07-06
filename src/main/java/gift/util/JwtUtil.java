package gift.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Base64;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    private static SecretKey getSigningKey(){
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public static String generateToken(String email, String password) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        String authInfo = email + ":" + password;
        String base64AuthInfo = Base64.getEncoder().encodeToString(authInfo.getBytes());

        return Jwts.builder()
            .setSubject(base64AuthInfo)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(getSigningKey())
            .compact();
    }

//    public static String extractClaims(String token) {
//        Claims claims = Jwts.parser()
//            .setSigningKey(SECRET_KEY)
//            .getClass(token)
//            .getBody();
//        String base64AuthInfo = claims.getSubject();
//        String authInfo = new String(Base64.getDecoder().decode(base64AuthInfo));
//        return authInfo;
//    }
}
