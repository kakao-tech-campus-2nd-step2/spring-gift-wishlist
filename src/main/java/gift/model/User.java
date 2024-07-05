package gift.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record User (
    @Email String email,
    @NotEmpty String password
) {

}
