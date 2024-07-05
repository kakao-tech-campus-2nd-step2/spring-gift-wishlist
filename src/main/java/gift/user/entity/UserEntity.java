package gift.user.entity;

import gift.user.dto.TokenDto;
import gift.user.utility.TokenUtility;

// User DB와 mapping될 엔터티
public class UserEntity {
    private String email;
    private String password;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // 정보를 토대로 토큰을 반환. 유일하게 토큰을 생성하는 곳
    public TokenDto getToken() {
        return new TokenDto(TokenUtility.getToken(email, password));
    }

    // 입력으로 들어온 비밀번호를 검증하는 로직
    public boolean verifyPassword(String password) {
        if (this.password == password) {
            return true;
        }

        return false;
    }
}
