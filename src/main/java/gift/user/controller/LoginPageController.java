package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {
    private static final String LOGIN_PAGE = "html/login";

    @GetMapping("/users/login-page")
    public String getLoginPage() {
        return LOGIN_PAGE;
    }
}
