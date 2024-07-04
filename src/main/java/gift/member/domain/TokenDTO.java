package gift.member.domain;

public class TokenDTO {

    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }

    public String getAccessToken() {
        return token;
    }

}
