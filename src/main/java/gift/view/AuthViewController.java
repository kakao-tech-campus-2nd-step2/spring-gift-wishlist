package gift.view;

import gift.controller.UserController;
import gift.model.Product;
import gift.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class AuthViewController {
    private final UserController userController;

    public AuthViewController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("/new")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User(0L, "", ""));
        return "singUp";
    }
    @PostMapping("")
    public String createUser(@ModelAttribute User user) {
        userController.createUser(user);
        return "redirect:/api/users";
    }
}
