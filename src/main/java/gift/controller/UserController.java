package gift.controller;

import gift.Dto.User;
import gift.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final LoginService loginService;

    @Autowired
    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginRendering() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        //loginService.
        // 여기서 service logic 불러온 후 , token 검증 해야함
        // 이후 버튼의 타입에 따라서 admin page로 가거나, user page로 간다.
        /*
         * if(!loginService.isExistUser(user)) return "error";
         * loginService.login(user);
         * */
        return "HI";


    }

    @GetMapping("/user-info")
    public ResponseEntity<List<User>> userInfoRendering() {
        List<User> users = loginService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/signup")
    public String signupRendering() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user){
        if(loginService.saveUser(user)){
            return "login";
        }
        return "error";
    }

}
