package gift.mapper;

import gift.dto.UserResponseDTO;
import gift.entity.User;

public class UserMapper {
    public static UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(user.id, user.email);
    }
}
