package gift.dto;

import jakarta.validation.constraints.Email;

public record UserDTO(
        String password,
        @Email
        String email
) {
}
