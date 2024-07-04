package gift.domain.user.service.jwt;

import gift.domain.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private static final long ACCESSTOKEN_EXPIRATION_TIME = 30 * 60 * 1000;

    private String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public String generateToken(User user) {

        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + ACCESSTOKEN_EXPIRATION_TIME);

        return Jwts.builder()
            .setSubject(user.getId().toString())
            .claim("name", user.getName())
            .claim("email", user.getEmail())
            .claim("role", user.getRole())
            .setExpiration(accessTokenExpiresIn)
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }
}
