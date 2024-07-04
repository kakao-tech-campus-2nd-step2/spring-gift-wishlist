package gift.service;

import gift.util.Vars;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean authenticate(String token) {
        Jws<Claims> jwt;
        jwt = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Vars.secretKey.getBytes()))
                .build()
                .parseSignedClaims(token);
        Object payload = jwt.getPayload();
        System.out.println("payload = " + payload);
        return true;
    }
}
