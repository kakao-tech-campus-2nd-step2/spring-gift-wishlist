package gift.controller;

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
    public ResponseEntity<UserDTO> RegisterUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO DTO = userService.RegisterUser(userDTO);
        return new ResponseEntity<>(DTO, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@Valid @RequestBody UserDTO userDTO) {
        String token = userService.Login(userDTO);
        return ResponseEntity.ok().header("Authorization", "Bearer " + token).build();
    }

}

