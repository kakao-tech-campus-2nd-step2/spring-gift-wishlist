package gift.service;

import gift.domain.model.JoinRequestDto;
import gift.domain.repository.UserRepository;
import gift.exception.DuplicateEmailException;
import gift.util.JwtUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String joinUser(JoinRequestDto joinRequestDto) {
        if (userRepository.isExistEmail(joinRequestDto.getEmail())) {
            throw new DuplicateEmailException("이미 가입한 이매일입니다.");
        }

        String hashedPassword = BCrypt.hashpw(joinRequestDto.getPassword(), BCrypt.gensalt());
        joinRequestDto.setPassword(hashedPassword);
        userRepository.save(joinRequestDto);

        return jwtUtil.generateToken(joinRequestDto.getEmail());
    }
}
