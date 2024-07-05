package gift.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public String loginPage() {
        return "login";
    }
}
