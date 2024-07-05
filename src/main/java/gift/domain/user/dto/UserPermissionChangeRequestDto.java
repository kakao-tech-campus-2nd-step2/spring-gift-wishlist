package gift.domain.user.dto;

import gift.domain.user.User;

public record UserPermissionChangeRequestDto(String email, String permission) {

    public static UserPermissionChangeRequestDto of(User user) {
        return new UserPermissionChangeRequestDto(user.email(), user.permission());
    }

    public static User toEntity(UserPermissionChangeRequestDto dto, String password) {
        return new User(dto.email(), password, dto.permission());
    }
}