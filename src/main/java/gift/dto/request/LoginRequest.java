package gift.dto.request;

public class LoginRequest {
    
    String password;
    String email;

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }
}
