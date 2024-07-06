package gift.user.controller;

import gift.user.exception.ForbiddenException;
import gift.user.jwt.JwtService;
import gift.user.model.UserRepository;
import gift.user.model.dto.FindPasswordRequest;
import gift.user.model.dto.LoginRequest;
import gift.user.model.dto.SignUpRequest;
import gift.user.model.dto.UpdatePasswordRequest;
import gift.user.model.dto.User;
import gift.user.resolver.LoginUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.SHA256Util;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        String salt = SHA256Util.getSalt();
        String hashedPassword = SHA256Util.encodePassword(signUpRequest.getPassword(), salt);
        signUpRequest.setPassword(hashedPassword);
        if (userRepository.signUpUser(signUpRequest, salt) > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ok");
        }
        throw new IllegalArgumentException("회원가입 실패");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null) {
            String hashedInputPassword = SHA256Util.encodePassword(loginRequest.getPassword(), user.salt());
            if (hashedInputPassword.equals(user.password())) {
                String token = jwtService.createToken(user.id());
                return ResponseEntity.ok()
                        .header("Authorization", token)
                        .body("로그인 성공");
            }
        }
        throw new ForbiddenException("로그인 실패");
    }

    @PatchMapping("/password")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest,
                                                 @LoginUser User loginUser) {
        Long id = loginUser.id();
        String storedPassword = loginUser.password();
        String salt = loginUser.salt();

        String hashedOldPassword = SHA256Util.encodePassword(updatePasswordRequest.getOldPassword(), salt);

        if (storedPassword.equals(hashedOldPassword)) {
            String newHashedPassword = SHA256Util.encodePassword(updatePasswordRequest.getNewPassword(), salt);
            if (userRepository.updatePassword(id, newHashedPassword) > 0) {
                return ResponseEntity.ok().body("ok");
            }
        }
        throw new ForbiddenException("비밀번호 변경 실패");
    }

    @GetMapping("/password")
    public ResponseEntity<String> findPassword(@Valid @RequestBody FindPasswordRequest findPasswordRequest,
                                               @LoginUser User loginUser) {
        if (loginUser.email().equals(findPasswordRequest.getEmail())) {
            return ResponseEntity.ok().body(loginUser.password());
        }
        throw new ForbiddenException("비밀번호 찾기 실패");
    }
}
