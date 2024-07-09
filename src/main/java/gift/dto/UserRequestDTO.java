package gift.dto;

public class UserRequestDTO {
    private final String email;
    private final String password;

    public UserRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
