package gift.controller;

import gift.dto.requestDTO.UserRequestDTO;
import gift.dto.responseDTO.UserResponseDTO;
import gift.service.AuthService;
import gift.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserRequestDTO userRequestDTO){
        userService.join(userRequestDTO);
        UserResponseDTO userResponseDTO = authService.register(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO){
        userService.findByEmail(userRequestDTO);
        UserResponseDTO userResponseDTO = authService.login(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
}
