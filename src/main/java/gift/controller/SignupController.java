package gift.controller;

import gift.DTO.SignupRequest;
import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignupForm(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute SignupRequest signupRequest, Model model) {
        try {
            userService.registerUser(signupRequest);
            return "redirect:/login"; // 회원가입 성공 시 로그인 페이지로 리디렉션
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "signup"; // 회원가입 실패 시 다시 회원가입 페이지로
        }
    }
}
