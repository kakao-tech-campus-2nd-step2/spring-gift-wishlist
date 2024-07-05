package gift.dto.requestDTO;

import gift.domain.User;

public record UserRequestDTO(String email, String password) {

    public static User toEntity(UserRequestDTO userRequestDTO) {
        return new User(userRequestDTO.email(), userRequestDTO.password());
    }
}
