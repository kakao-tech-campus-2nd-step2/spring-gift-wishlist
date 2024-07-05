package gift;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUp(SignUpForm signUpForm) {
        String email = signUpForm.getEmail();
        if (userRepository.findByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이메일은 사용할 수 없습니다");
        }

        UserRole role = signUpForm.getRole();
        if (UserRole.ADMIN == role) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ADMIN으로 가입하실 수 없습니다.");
        }

        String password = passwordEncoder.encode(signUpForm.getPassword());
        String name = signUpForm.getName();

        User user = new User(email, password, name, role);
        userRepository.save(user);
    }
}
