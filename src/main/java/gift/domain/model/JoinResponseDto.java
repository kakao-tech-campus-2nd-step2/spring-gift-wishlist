package gift.domain.model;

public class JoinResponseDto {
    private String token;

    public JoinResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
