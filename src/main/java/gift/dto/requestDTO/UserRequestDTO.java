package gift.dto.requestDTO;

import gift.domain.User;

public record UserRequestDTO(String email, String password, String role) {

    public static User toEntity(UserRequestDTO userRequestDTO) {
        return new User(userRequestDTO.email(), userRequestDTO.password(), userRequestDTO.role());
    }
}
