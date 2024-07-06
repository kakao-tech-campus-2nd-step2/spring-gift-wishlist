package gift.service;

import gift.auth.JwtUtil;
import gift.domain.Role;
import gift.domain.User;
import gift.dto.requestDTO.UserRequestDTO;
import gift.dto.responseDTO.UserResponseDTO;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        String token = jwtUtil.createToken(userRequestDTO.email(), Role.USER.name());
        return new UserResponseDTO(token);
    }

    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        User user = UserRequestDTO.toEntity(userRequestDTO);
        if (!user.getPassword().equals(userRequestDTO.password())) {
            throw new NoSuchElementException("회원의 정보가 일치하지 않습니다.");
        }
        String token = jwtUtil.createToken(user.getEmail(), user.getRole());
        return new UserResponseDTO(token);
    }
}
