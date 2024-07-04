package gift.member.domain;

public class LoginResponseDTO {

    private String accessToken;

    public LoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
