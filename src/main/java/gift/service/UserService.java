package gift.service;

import gift.dto.UserRequestDto;
import gift.dto.UserResponseDto;
import gift.entity.User;
import gift.exception.UserNotFoundException;
import gift.repository.UserRepository;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto registerUser(UserRequestDto userRequest) {
        Long id = userRepository.save(userRequest);
        return new UserResponseDto(
            id,
            userRequest.email(),
            getToken(userRequest.email(), userRequest.password())
        );
    }

    public UserResponseDto loginUser(UserRequestDto userRequest) {
        User user = userRepository.findByEmailAndPassword(userRequest.email(), userRequest.password())
            .orElseThrow(() -> new UserNotFoundException("로그인할 수 없습니다."));

        return new UserResponseDto(
            user.id(),
            user.password(),
            getToken(userRequest.email(), userRequest.password())
        );
    }

    private String getToken(String email, String password) {
        return Base64.getEncoder()
            .encodeToString((email + password).getBytes());
    }

}
