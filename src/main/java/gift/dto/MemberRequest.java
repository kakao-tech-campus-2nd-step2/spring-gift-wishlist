package gift.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MemberRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public CharSequence getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // getters and setters
}
