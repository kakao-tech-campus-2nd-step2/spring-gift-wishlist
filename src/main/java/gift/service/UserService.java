package gift.service;

import gift.DTO.LoginRequest;
import gift.DTO.SignupRequest;
import gift.DTO.User;
import gift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(SignupRequest signupRequest) {
        User user = new User(signupRequest.getEmail(), signupRequest.getPassword());
        userRepository.addUser(user);
        return "Welcome, " + user.getEmail() + "!";
    }

    public String loginUser(LoginRequest loginRequest) throws Exception {
        User user = userRepository.findUserByEmail(loginRequest.getEmail()); // Email이 PK
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return generateToken(user);
        } else {
            throw new Exception("Invalid email or password");
        }
    }

    private String generateToken(User user) {
        // 토큰 생성 로직 구현하기...
        return "dummy-token";
    }
}

