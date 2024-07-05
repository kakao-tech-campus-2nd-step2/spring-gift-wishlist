package gift.model.dto;

public class LoginUserDto {

    Long id;
    String name;
    String email;
    String role;

    public LoginUserDto(Long id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
