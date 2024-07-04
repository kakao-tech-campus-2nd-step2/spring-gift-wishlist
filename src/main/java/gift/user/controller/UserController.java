package gift.user.controller;

import gift.user.exception.ForbiddenException;
import gift.user.jwt.JwtService;
import gift.user.model.UserRepository;
import gift.user.model.dto.FindPasswordRequest;
import gift.user.model.dto.LoginRequest;
import gift.user.model.dto.SignUpRequest;
import gift.user.model.dto.UpdatePasswordRequest;
import gift.user.model.dto.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (userRepository.signUpUser(signUpRequest) > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ok");
        }
        throw new IllegalArgumentException("회원가입 실패");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepository.checkUserByEmail(loginRequest);
        if (user != null) {
            String token = jwtService.createToken(user.getId(), user.getRole());
            return ResponseEntity.ok()
                    .header("Authorization", token)
                    .body("로그인 성공");
        }
        throw new ForbiddenException("로그인 실패");
    }

    @PatchMapping("/password")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest,
                                                 @RequestHeader("Authorization") String token) {
        Long id = jwtService.getIdFromToken(token);
        String password = userRepository.findUser(id).getPassword();
        if (password.equals(updatePasswordRequest.getOldPassword())) {
            if (userRepository.updatePassword(id, updatePasswordRequest.getNewPassword()) > 0) {
                return ResponseEntity.ok().body("ok");
            }
        }
        throw new ForbiddenException("비밀번호 변경 실패");
    }

    @GetMapping("/password")
    public ResponseEntity<String> findPassword(@Valid @RequestBody FindPasswordRequest findPasswordRequest,
                                               @RequestHeader("Authorization") String token) {
        Long id = jwtService.getIdFromToken(token);
        User user = userRepository.findUser(id);
        if (user.getEmail().equals(findPasswordRequest.getEmail())) {
            return ResponseEntity.ok().body(user.getPassword());
        }
        throw new ForbiddenException("비밀번호 찾기 실패");
    }
}
