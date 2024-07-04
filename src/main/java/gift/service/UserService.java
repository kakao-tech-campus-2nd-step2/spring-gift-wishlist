package gift.service;

import gift.domain.model.UserRequestDto;
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

    public String joinUser(UserRequestDto userRequestDto) {
        if (userRepository.isExistEmail(userRequestDto.getEmail())) {
            throw new DuplicateEmailException("이미 가입한 이매일입니다.");
        }

        String hashedPassword = BCrypt.hashpw(userRequestDto.getPassword(), BCrypt.gensalt());
        userRequestDto.setPassword(hashedPassword);
        userRepository.save(userRequestDto);

        return jwtUtil.generateToken(userRequestDto.getEmail());
    }
}
