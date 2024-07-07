package gift.domain.user;

import gift.domain.user.dto.UserRequestDto;
import gift.domain.user.dto.UserResponseDto;
import gift.domain.user.exception.UserAlreadyExistsException;
import gift.domain.user.exception.UserIncorrectLoginInfoException;
import gift.global.util.HashUtil;
import gift.global.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDto registerUser(UserRequestDto requestDto) {

        // 기존 회원인 경우 예외
        userRepository.findByEmail(requestDto.email()).ifPresent(p -> {
            throw new UserAlreadyExistsException();
        });

        return new UserResponseDto(jwtUtil.generateToken(userRepository.save(requestDto)));
    }

    public UserResponseDto loginUser(UserRequestDto requestDto) {
        // 존재하지 않은 이메일을 가진 유저로 로그인 시도
        // 존재한 경우 user 참조 반환
        User user = userRepository.findByEmail(requestDto.email())
            .orElseThrow(UserIncorrectLoginInfoException::new);

        // 유저는 존재하나 비밀번호가 맞지 않은 채 로그인 시도
        if (!HashUtil.hashCode(requestDto.password()).equals(user.password())) {
            throw new UserIncorrectLoginInfoException();
        }

        return new UserResponseDto(jwtUtil.generateToken(user));
    }

    public User getUserByToken(String token) {
        return userRepository.findByEmail(jwtUtil.getSubject(token))
            .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    public boolean isAdmin(String token) {
        return jwtUtil.getPermission(token).equals("admin");
    }
}
