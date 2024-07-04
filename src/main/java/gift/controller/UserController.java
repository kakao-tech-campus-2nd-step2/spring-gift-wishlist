package gift.controller;

import gift.model.User;
import gift.repository.UserRepository;
import gift.util.UserUtility;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody @Valid User user) {
        userRepository.save(user);
        String accessToken = UserUtility.makeAccessToken(user);
        return ResponseEntity.ok().body(UserUtility.stringToObject(accessToken));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid User user) {
        Optional<User> result = userRepository.findByEmail(user.getEmail());
        if (!result.isPresent())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email does not exist");
        User foundUser = result.get();
        if (!user.getPassword().equals(foundUser.getPassword()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Password does not match");
        String accessToken = UserUtility.makeAccessToken(user);
        return ResponseEntity.ok().body(UserUtility.stringToObject(accessToken));
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleForbidden(ResponseStatusException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
