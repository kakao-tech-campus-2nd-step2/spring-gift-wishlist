package gift.controller.user.dto;

import gift.model.user.Role;
import gift.model.user.User;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    public record Register(
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String name) {

        public User toEntity() {
            return User.create(null, email(), password(), name(), Role.USER);
        }
    }

    public record Login(
        @NotBlank
        String email,
        @NotBlank
        String password) {

    }
}
