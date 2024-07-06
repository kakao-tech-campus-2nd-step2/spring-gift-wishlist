package gift.model;

import io.jsonwebtoken.Jwts.SIG;
import java.security.Key;
import java.util.Objects;

public class LoginToken {

    private String token;

    private Key key = SIG.HS256.key().build();

    private String email;
    private String role;
    private Long memberId;

    public LoginToken() {
    }

    public LoginToken(Long memberId, String email, MemberRole role) {
        this.token = email + ":" + role.toString();
        /* JWT 학습 이후 구현하기
        this.token = Jwts.builder()
            .setSubject(email)
            .claim("role", role.toString())
            .signWith(key)
            .compact();

         */
        this.email = email;
        this.role = role.toString();
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        if (token != null) {
            return memberId;
        }
        //JwtParser parser = Jwts.parser().build();
        return null;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoginToken that = (LoginToken) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token);
    }


}
