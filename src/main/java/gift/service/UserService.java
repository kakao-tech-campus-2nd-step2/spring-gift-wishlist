package gift.service;

import gift.controller.UserDTO;
import gift.model.User;
import gift.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserDTO userDTO) {
        validateUser(userDTO);
        User user = new User(null, userDTO.name(), userDTO.email(), userDTO.password(), "user");
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void validateUser(UserDTO userDTO) {
        if (userDTO.name() == null || userDTO.name().isBlank()) {
            throw new IllegalArgumentException("이름을 입력하세요.");
        }
        if (userDTO.email() == null || userDTO.email().isBlank()) {
            throw new IllegalArgumentException("이메일을 입력하세요.");
        }
        if (userDTO.email() == null || !userDTO.email()
            .matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$]")) {
            throw new IllegalArgumentException("유효한 이메일을 입력하세요.");
        }
        if (userDTO.password() == null || userDTO.password().isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력하세요.");
        }
    }

}