package gift.controller;

import gift.dto.PwUpdateDTO;
import gift.dto.UserRequestDTO;
import gift.dto.UserResponseDTO;
import gift.exception.ForbiddenRequestException;
import gift.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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



    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/api/{id}/password")
    public ResponseEntity<String> updatePw(@PathVariable long id, @RequestBody @Valid PwUpdateDTO pwUpdateDTO) {
        final boolean FORBIDDEN = true;

        if (FORBIDDEN) {
            throw new ForbiddenRequestException("password changing is not allowed");
        }

        userService.updatePw(id, pwUpdateDTO);

        return ResponseEntity.ok("Password updated successfully");
    }
}
