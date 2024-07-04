package gift.dto;

public class UserLoginDTO {
    public final String email;
    public final String password;

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
