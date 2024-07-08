package gift.controller;

import gift.dto.TokenResponseDTO;
import gift.dto.UserRequestDTO;
import gift.dto.UserResponseDTO;
import gift.exception.InvalidPasswordException;
import gift.service.UserService;
import gift.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/api/signup")
    public ResponseEntity<TokenResponseDTO> signUp(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.signUp(userRequestDTO);
        String token = jwtUtil.generateToken(userResponseDTO.email());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }


    @PostMapping("/api/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UserRequestDTO userRequestDTO) throws InvalidPasswordException {
        UserResponseDTO userResponseDTO = userService.login(userRequestDTO);
        String token = jwtUtil.generateToken(userResponseDTO.email());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
