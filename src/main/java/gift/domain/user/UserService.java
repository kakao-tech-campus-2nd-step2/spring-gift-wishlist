package gift.domain.user;

import gift.global.Util.JwtUtil;
import gift.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepositoryInterface userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 가입
     * @param userDTO 가입 시 입력 정보
     */
    public void join(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BusinessException(HttpStatus.CONFLICT, "해당 이메일의 회원이 이미 존재합니다.");
        }
        userRepository.join(userDTO);
    }


    /**
     * 로그인 성공 시 Jwt 반환
     * @param userDTO
     * @return Jwt
     */
    public String login(UserDTO userDTO) {
        boolean isExists = userRepository.checkUserInfo(userDTO);
        if (!isExists) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "로그인 입력 정보가 올바르지 않습니다.");
        }
        // jwt 토큰 생성
        String jwt = JwtUtil.generateToken(userDTO.getEmail());
        
        return jwt;
    }
}
