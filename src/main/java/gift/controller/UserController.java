package gift.controller;

import gift.dto.UserRequestDTO;
import gift.dto.UserResponseDTO;
import gift.entity.ProductRecord;
import gift.repository.UserDAO;
import gift.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/api/signup")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.signUp(userRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/api/users/"+ userResponseDTO.id())
                .build()
                .toUri();

        return ResponseEntity.created(location).body(userResponseDTO);
    }
}
