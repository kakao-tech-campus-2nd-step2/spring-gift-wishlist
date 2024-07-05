package gift.service;

import gift.dto.UserRequestDto;
import gift.dto.UserResponseDto;
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



    private String getToken(String email, String password) {
        return Base64.getEncoder()
            .encodeToString((email + password).getBytes());
    }

}
