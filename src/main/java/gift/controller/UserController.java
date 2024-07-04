package gift.controller;

import gift.controller.dto.request.UserSignupRequest;
import gift.controller.dto.response.UserSignInResponse;
import gift.model.JwtUtil;
import gift.model.User;
import gift.model.repository.UserRepository;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponse signUp(@RequestBody UserSignupRequest userSignupRequest) {
        User user = userSignupRequest.toModel();

        Optional<User> existingUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("같은 이름의 사용자가 이미 존재합니다.");
        }

        userRepository.save(user);
        User savedUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        String token = JwtUtil.generateToken(savedUser);
        return new UserSignInResponse(token);
    }
}
