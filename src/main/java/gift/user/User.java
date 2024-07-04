package gift.user;

import jakarta.validation.constraints.Email;

public record User(
    @Email(message = "This is not an email format")
    String email,
    String password) {

}
