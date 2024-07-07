package gift.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class MemberAccessTokenProvider {

    public static String createJwt(String email){
        String secretKey = "21312BHV48DX21421C25F215NJN2DF12141JJ214GVSQ12429858236H7998325A72";
        return Jwts.builder()
            .setHeaderParam("type","jwt")
            .claim("email", email)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }
}
