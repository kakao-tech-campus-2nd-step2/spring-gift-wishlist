package gift.dto.request;

import jakarta.validation.constraints.Size;

public class LoginRequest {

    @Size(max = 20)
    String password;
    String email;

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }
}
