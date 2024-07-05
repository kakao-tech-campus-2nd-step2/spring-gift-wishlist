package gift.domain.user.dto;

import gift.domain.user.User;

public record UserRequestDto(String email, String password) {

    public static UserRequestDto of(User user) {
        return new UserRequestDto(user.email(), user.password());
    }

    public static User toEntity(UserRequestDto dto, String permission) {
        return new User(dto.email(), dto.password(), permission);
    }
}
