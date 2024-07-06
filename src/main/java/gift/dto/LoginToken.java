package gift.dto;

import gift.model.MemberRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.util.Objects;

public class LoginToken {

    private String token;

    public LoginToken() {
    }

    public LoginToken(String email, MemberRole role) {
        this.token = Jwts.builder()
            .setSubject(email)
            .claim("role", role.toString())
            .signWith(SIG.HS256.key().build())
            .compact();
    }

    public String getToken() {
        return token;
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
