package gift.Controller;

import gift.Model.Product;
import gift.Model.User;
import gift.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private JwtTokenProvider tokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute("user", new User());
            return "login";
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute("user", user);
            List<User> users = userService.login(user);
            if (users.isEmpty()) {
                model.addAttribute("error", "유효하지 않은 아이디거나 비밀번호입니다.");
                return "login";
            }

            String email = user.getEmail();
            boolean isAdmin = userService.isAdmin(email);
            String jwt = tokenProvider.generateToken(user, isAdmin);
            model.addAttribute("token", jwt);
            if (isAdmin) {
                return "redirect:/api/products";
            }
            return "redirect:/products";
        }
        return "login";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(@ModelAttribute User user, Model model, HttpServletRequest request) {
        if("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute("user", new User());
            return "register";
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute("user", user);
            userService.register(user);
        }
        return "login";
    }
}
