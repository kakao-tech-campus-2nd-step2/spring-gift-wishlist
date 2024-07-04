package gift.api;

public class MemberResponseDto {

    private String token;

    public MemberResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
