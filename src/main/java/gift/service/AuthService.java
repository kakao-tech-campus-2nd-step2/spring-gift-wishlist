package gift.service;

import gift.auth.JwtTokenGenerator;
import gift.domain.Role;
import gift.domain.User;
import gift.dto.requestDTO.UserRequestDTO;
import gift.dto.responseDTO.UserResponseDTO;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtTokenGenerator jwtTokenGenerator;
    private final UserService userService;

    public AuthService(JwtTokenGenerator jwtTokenGenerator, UserService userService) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.userService = userService;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        userService.join(userRequestDTO);
        String token = jwtTokenGenerator.createToken(userRequestDTO.email(), Role.USER.name());
        return new UserResponseDTO(token);
    }

    public UserResponseDTO login(UserRequestDTO userRequestDTO){
        User user = userService.findByEmail(userRequestDTO);
        if(!user.getPassword().equals(userRequestDTO.password())){
            throw new NoSuchElementException("비밀번호가 일치하지 않습니다.");

        }
        String token = jwtTokenGenerator.createToken(userRequestDTO.email(), user.getRole());
        return new UserResponseDTO(token);
    }
}
