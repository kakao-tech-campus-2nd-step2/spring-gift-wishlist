package gift.controller;

import gift.common.exception.UserAlreadyExistsException;
import gift.common.exception.UserNotFoundException;
import gift.controller.dto.request.UserSignUpRequest;
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
    public UserSignInResponse signUp(@RequestBody UserSignUpRequest userSignupRequest) {
        User user = userSignupRequest.toModel();

        Optional<User> existingUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        userRepository.save(user);
        User savedUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(UserNotFoundException::new);

        String token = JwtUtil.generateToken(savedUser);
        return new UserSignInResponse(token);
    }
}
