package gift.user.restapi;

import gift.core.domain.user.User;
import gift.core.domain.user.UserAccount;
import gift.core.domain.user.UserService;
import gift.user.restapi.dto.request.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/api/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody RegisterUserRequest request) {
        userService.registerUser(userOf(request));
    }

    private User userOf(RegisterUserRequest request) {
        return new User(
                0L,
                request.name(),
                new UserAccount(
                        request.email(),
                        request.password()
                )
        );
    }
}
