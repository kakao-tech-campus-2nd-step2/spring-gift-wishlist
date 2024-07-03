package gift.model.Login;

public class JwtToken {
    private String grantType;
    private String accessToken;

    // 기본 생성자
    public JwtToken() {}

    // 모든 매개변수가 있는 생성자
    public JwtToken(String grantType, String accessToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
    }

    // Getter와 Setter
    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
