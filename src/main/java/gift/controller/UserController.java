package gift.controller;

import gift.dto.UserLoginDTO;
import gift.dto.UserRegisterDTO;
import gift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.registerUser(userRegisterDTO.email, userRegisterDTO.password);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        String token = userService.loginUser(userLoginDTO.email, userLoginDTO.password);
        return new ResponseEntity<>("{\"accessToken\": \"" + token + "\"}", HttpStatus.OK);
    }
}
