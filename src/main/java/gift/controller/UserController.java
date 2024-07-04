package gift.controller;

import gift.dto.UserLoginDto;
import gift.dto.UserRegisterDto;
import gift.dto.UserResponseDto;
import gift.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegisterDto userRegisterDTO) {
        UserResponseDto userResponseDTO = userService.registerUser(userRegisterDTO.email, userRegisterDTO.password);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userLoginDTO) {
        String token = userService.loginUser(userLoginDTO.email, userLoginDTO.password);
        return new ResponseEntity<>("{\"accessToken\": \"" + token + "\"}", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRegisterDto userRegisterDTO) {
        UserResponseDto updatedUser = userService.updateUser(id, userRegisterDTO.email, userRegisterDTO.password);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
