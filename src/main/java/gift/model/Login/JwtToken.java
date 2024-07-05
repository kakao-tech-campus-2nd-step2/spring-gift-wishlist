package gift.model.Login;

public class JwtToken {
    private String grantType;
    private String accessToken;

    // 모든 매개변수가 있는 생성자
    public JwtToken(String grantType, String accessToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
