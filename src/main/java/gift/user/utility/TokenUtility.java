package gift.user.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

// JWT 토큰을 생성해주는 utility 클래스
public class TokenUtility {

    // 보안을 위해 token을 업데이트할 수 있도록 final로 선언하지 않기
    private static String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    // 입력한 정보를 토대로 토큰을 반환하는 함수
    public static String getToken(String email, String password) {
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
            .subject(email.toString())
            .claim("password", password)
            .issuedAt(new Date(currentTime))
            // 유효기간은 60분
            .expiration(new Date(currentTime + minuteToMillis(60)))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

    // minute을 넣으면 밀리초로 반환하는 메서드
    private static long minuteToMillis(int minute) {
        return minute * 60L * 1000;
    }
}
