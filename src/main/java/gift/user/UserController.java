package gift.user;


import gift.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final JWTService jwtService;
    public UserController(ProductRepository productRepository, UserService userService, JWTService jwtService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    //로그인 화면
    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        return "Login";
    }

    //회원 가입 화면
    @GetMapping("/signup")
    public String signupView(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "SignUp";
    }

    //메인 화면
    @GetMapping("/main")
    public String mainView(Model model){
        model.addAttribute("products", productRepository.selectProducts());
        return "MainView";
    }

    //로그인 API
    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "Login";
        }

        if(!userService.validateUser(loginDTO)){
            return "Login";
        }
        UserDTO userDTO = userService.getUserDTOByLoginDTO(loginDTO);
        String accessToken = jwtService.generateAccessToken(userDTO);
        redirectAttributes.addAttribute("accessToken", accessToken);
        return "redirect:/user/main";
    }

}
