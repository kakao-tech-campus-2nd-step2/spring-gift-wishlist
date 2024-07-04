package gift.user.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// JWT 토큰을 생성해주는 utility 클래스
public class SecurityUtility {

    // 보안을 위해 token을 업데이트할 수 있도록 final로 선언하지 않기
    private static String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    // 입력한 정보를 토대로 토큰을 반환하는 함수
    public static String getToken(String email, String password) {
        return Jwts.builder()
            .setSubject(email.toString())
            .claim("password", password)
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }
}
