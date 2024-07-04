package gift.user;

import gift.token.Token;
import jakarta.validation.Valid;
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
    public Token register(@Valid @RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Token login(@Valid @RequestBody User user) {
        return userService.login(user);
    }
}
