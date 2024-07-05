package gift.service;

import gift.dto.UserRequestDto;
import gift.dto.UserResponseDto;
import gift.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto registerUser(UserRequestDto userRequest) {
        Long id = userRepository.save(userRequest);
        return new UserResponseDto(id, userRequest.email());
    }

}
