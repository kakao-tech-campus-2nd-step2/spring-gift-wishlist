package gift.user;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateAccessToken(UserDTO userDTO){
        Date now = new Date();

        return Jwts.builder()
                    .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                    .setIssuedAt(now)
                    .setExpiration(createExpiredDate(now))
                    .signWith(key)
                    .compact();
        }


    private Date createExpiredDate(Date now) {
        return new Date(now.getTime() + (60 * (60 * 1000)));
    }

}
