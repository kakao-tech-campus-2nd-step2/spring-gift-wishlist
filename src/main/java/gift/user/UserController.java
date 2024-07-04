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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@RestController
@RequestMapping("/members")
public class UserController {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final JWTService jwtService;
    public UserController(ProductRepository productRepository, UserService userService, JWTService jwtService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    //로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult){
        if(!bindingResult.hasErrors() && !userService.validateUser(loginDTO)){
            bindingResult.addError(new FieldError("loginDTO", "password", "이메일이 존재하지 않거나 비밀번호가 일치하지 않습니다"));
        }

        if(bindingResult.hasErrors()){
            HashMap<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                map.put(err.getField(), err.getDefaultMessage());
            });

            ResponseDTO responseDTO = new ResponseDTO(
                    HttpStatus.FORBIDDEN,
                    "Validation Error",
                    map
            );
            return ResponseEntity.badRequest().body(responseDTO);
        }

        UserDTO userDTO = userService.getUserDTOByLoginDTO(loginDTO);
        String accessToken = jwtService.generateAccessToken(userDTO);

        return ResponseEntity.ok(new Token(accessToken));
    }

    //회원 가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        if(userService.checkIfDuplicatedEmail(userDTO.email)){
            bindingResult.addError(new FieldError("userDTO", "email", "이미 등록된 이메일입니다."));
        }

        if(bindingResult.hasErrors()){
            HashMap<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                map.put(err.getField(), err.getDefaultMessage());
            });

            ResponseDTO responseDTO = new ResponseDTO(
                    HttpStatus.FORBIDDEN,
                    "Validation Error",
                    map
            );
            return ResponseEntity.badRequest().body(responseDTO);
        }

        userService.registerUser(userDTO);
        String accessToken = jwtService.generateAccessToken(userDTO);
        return ResponseEntity.ok(new Token(accessToken));
    }
}
