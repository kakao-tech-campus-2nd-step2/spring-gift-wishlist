package gift.service;

import gift.dto.UserRequestDTO;
import gift.exception.DuplicateException;
import gift.model.User;
import gift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(UserRequestDTO userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new DuplicateException("이미 존재하는 회원입니다.");
        }
        User user = new User(userRequest.getEmail(), userRequest.getPassword());
        userRepository.save(user);
    }

    public String authenticate(UserRequestDTO userRequest) {
        Optional<User> userOpt = userRepository.findByEmail(userRequest.getEmail());
        if (userOpt.isEmpty() || !userOpt.get().checkPassword(userRequest.getPassword())) {
            throw new IllegalArgumentException("유효하지 않은 이메일 or 비밀번호입니다.");
        }
        return "access-token";
    }
}
