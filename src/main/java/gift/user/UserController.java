package gift.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registerPage() {
        return "register";
    }
}
