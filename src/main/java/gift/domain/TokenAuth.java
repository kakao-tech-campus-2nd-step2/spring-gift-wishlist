package gift.domain;

public class TokenAuth {
    private String token;
    private String email;
    public TokenAuth(String token, String email){
        this.token = token;
        this.email = email;
    }
}
