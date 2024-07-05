package gift.service;

import gift.dto.user.UserRequestDto;
import gift.dto.user.UserResponseDto;
import gift.entity.User;
import gift.exception.user.UserNotFoundException;
import gift.exception.user.UserUnauthorizedException;
import gift.mapper.UserMapper;
import gift.repository.UserRepository;
import java.util.Base64;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto registerUser(UserRequestDto userRequest) {
        Long id = userRepository.insert(UserMapper.toUser(userRequest));
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

    public Long getUserIdByToken(String token) {
        HashMap<String, String> credentials = decodeToken(token);
        User user = userRepository.findByEmailAndPassword(
            credentials.get("email"),
            credentials.get("password"))
            .orElseThrow(() -> new UserUnauthorizedException("접근할 수 없습니다."));

        return user.id();
    }

    private HashMap<String, String> decodeToken(String token) {
        String decodedString = new String(Base64.getDecoder().decode(token));
        String[] parts = decodedString.split(":",2);
        HashMap<String,String> credentials = new HashMap<>();
        credentials.put("email", parts[0]);
        credentials.put("password", parts[1]);
        return credentials;
    }

    private String getToken(String email, String password) {
        return Base64.getEncoder()
            .encodeToString((email + ":" + password).getBytes());
    }

}