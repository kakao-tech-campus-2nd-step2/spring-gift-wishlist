package gift.Controller;

import gift.Model.User;
import gift.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO("", "", ""));
        return "register_user_form";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid UserDTO userDTO, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            return "register_user_form";
        }

        User existingUser = userRepository.findByEmail(userDTO.email());
        if (existingUser != null) {
            model.addAttribute("emailError", "이메일이 이미 존재합니다.");
            return "register_user_form";
        }

        User user = new User(null, userDTO.name(), userDTO.email(), userDTO.password(), "user");
        userRepository.save(user);

        return "register_success";
    }
}