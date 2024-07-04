package gift.dto;

public class LoginResponseDto {
    private String accessToken;

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
