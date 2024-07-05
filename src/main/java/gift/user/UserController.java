package gift.user;


import gift.ProductRepository;
import gift.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;

@RestController
@RequestMapping("/members")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;
    public UserController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){
        HashMap<String, String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            map.put(err.getField(), err.getDefaultMessage());
        });

        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.BAD_REQUEST,
                "Validation Error",
                map
        );
        return ResponseEntity.badRequest().body(responseDTO);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleAuthenticationException(IllegalArgumentException ex){
        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.BAD_REQUEST,
                "IllegalArgumentException",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);

    }

    //로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws Exception {
        if(!userService.validateUser(loginDTO)){
            throw new IllegalArgumentException("이메일이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
        }

        UserDTO userDTO = userService.getUserDTOByLoginDTO(loginDTO);
        String accessToken = jwtService.generateAccessToken(userDTO);

        return ResponseEntity.ok(new Token(accessToken));
    }

    //회원 가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody @Valid UserDTO userDTO){
        if(userService.checkIfDuplicatedEmail(userDTO.email)){
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        userService.registerUser(userDTO);
        String accessToken = jwtService.generateAccessToken(userDTO);
        return ResponseEntity.ok(new Token(accessToken));
    }
}
