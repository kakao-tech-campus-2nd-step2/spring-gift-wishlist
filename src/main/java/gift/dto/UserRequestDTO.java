package gift.dto;

public class UserRequestDTO {
    private final String email;
    private final String password;
    private final String token;

    public UserRequestDTO(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
