package gift.controller;

import gift.controller.dto.ChangePasswordDTO;
import gift.controller.dto.TokenResponse;
import gift.controller.dto.UserDTO;
import gift.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> registerUser(@Valid @RequestBody UserDTO userDTO){
        TokenResponse tokenResponse = userService.registerUser(userDTO);
        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginUser(@Valid @RequestBody UserDTO userDTO) {
        TokenResponse login = userService.login(userDTO);
        return ResponseEntity.ok().body(login);
    }

    @PostMapping("/chage")
    public ResponseEntity<Boolean> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO){
        boolean b = userService.changePasswordDTO(changePasswordDTO);
        return ResponseEntity.ok(b);
    }

    @PostMapping("/find")
    public ResponseEntity<UserDTO> findPassword(@RequestBody String email){
        UserDTO password = userService.findPassword(email);
        return ResponseEntity.ok(password);
    }

}

