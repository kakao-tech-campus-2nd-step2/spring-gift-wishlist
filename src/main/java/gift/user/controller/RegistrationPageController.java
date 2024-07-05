package gift.user.controller;

import gift.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 회원가입 페이지를 반환하는 컨트롤러
@Controller
public class RegistrationPageController {

    private final UserService userService;
    private static final String REGISTRATION_PAGE = "html/registration";

    @Autowired
    public RegistrationPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/registration-page")
    public String getRegistrationPage() {
        return REGISTRATION_PAGE;
    }
}
