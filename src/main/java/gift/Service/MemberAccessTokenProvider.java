package gift.Service;

import gift.Model.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class MemberAccessTokenProvider {

    public static String createJwt(String email){
        Date now = new Date();
        return Jwts.builder()
            .setHeaderParam("type","jwt")
            .claim("email", email)
            .setIssuedAt(now)
            .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
            .signWith(SignatureAlgorithm.HS256, SecretKey.JWT_SECRET_KEY)
            .compact();
    }
}
