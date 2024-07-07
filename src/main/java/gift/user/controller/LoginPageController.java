package gift.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 로그인 페이지를 보여주는 컨트롤러
@Controller
public class LoginPageController {
    private static final String LOGIN_PAGE = "html/login";

    @GetMapping("/users/login-page")
    public String getLoginPage() {
        return LOGIN_PAGE;
    }
}
