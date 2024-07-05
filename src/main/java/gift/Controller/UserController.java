package gift.Controller;

import gift.Exception.ForbiddenException;
import gift.Exception.UnauthorizedException;
import gift.Model.User;
import gift.Service.UserService;
import gift.Util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute("user", new User());
            return "login";
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                // Extract credentials from Basic Auth header
                String[] credentials = AuthUtil.extractCredentials(request);
                if (credentials == null || credentials.length < 2) {
                    throw new UnauthorizedException("유효하지 않은 인증 정보입니다.");
                }
                String email = credentials[0];
                String password = credentials[1];

                user.setEmail(email);
                user.setPassword(password);
                model.addAttribute("user", user);

                List<User> users = userService.login(user);
                if (users.isEmpty()) {
                    throw new ForbiddenException("유효하지 않은 아이디거나 비밀번호입니다.");
                }

                boolean isAdmin = userService.isAdmin(email);
                if (isAdmin) {
                    return "redirect:/api/products";
                }
                return "redirect:/products";
            } catch (UnauthorizedException | ForbiddenException e) {
                model.addAttribute("error", e.getMessage());
                return "login";
            }
        }
        return "login";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(@ModelAttribute User user, Model model, HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute("user", new User());
            return "register";
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute("user", user);
            userService.register(user);
            model.addAttribute("message", "회원가입에 성공했습니다.");
            return "login";
        }
        return "login";
    }
}
