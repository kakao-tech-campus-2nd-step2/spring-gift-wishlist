package gift.dto;

public class UserRegisterDTO {
    public final String email;
    public final String password;

    public UserRegisterDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

