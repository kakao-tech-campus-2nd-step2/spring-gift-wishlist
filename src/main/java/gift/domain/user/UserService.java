package gift.domain.user;

import gift.global.jwt.JwtProvider;
import gift.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepositoryInterface userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 가입
     */
    public void join(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BusinessException(HttpStatus.CONFLICT, "해당 이메일의 회원이 이미 존재합니다.");
        }
        userRepository.join(userDTO);
    }


    /**
     * 로그인, 성공 시 JWT 반환
     */
    public String login(UserDTO userDTO) {
        User user = userRepository.findByEmailAndPassword(userDTO);

        // jwt 토큰 생성
        String jwt = JwtProvider.generateToken(user);
        
        return jwt;
    }
}
