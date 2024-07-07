package wishlist.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wishlist.exception.CustomException.DuplicateEmailException;
import wishlist.exception.CustomException.EmailNotFoundException;
import wishlist.exception.CustomException.PassWordMissMatchException;
import wishlist.model.user.UserForm;
import wishlist.service.JwtProvider;
import wishlist.service.UserService;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> handleLoginRequest(@Valid @RequestBody UserForm userForm,
        BindingResult result)
        throws MethodArgumentNotValidException {
        if (!userService.existsEmail(userForm.getEmail())) {
            result.rejectValue("email", "", "해당 이메일은 존재하지 않습니다.");
            throw new EmailNotFoundException(null, result);
        }
        if (!userService.isPassWordMatch(userForm)) {
            result.rejectValue("passWord", "", "비밀번호가 일치하지 않습니다.");
            throw new PassWordMissMatchException(null, result);
        }
        return ResponseEntity.ok(jwtProvider.generateToken(userService.findByEmail(userForm.getEmail())));
    }

    @PostMapping("/register")
    public ResponseEntity<?> handleSignUpRequest(@Valid @RequestBody UserForm userForm,
        BindingResult result) throws MethodArgumentNotValidException {
        if (userService.existsEmail(userForm.getEmail())) {
            result.rejectValue("email", "", "이미 존재하는 이메일입니다.");
            throw new DuplicateEmailException(null, result);
        }
        Long id = userService.insertUser(userForm);
        return ResponseEntity.ok(id);
    }

}
