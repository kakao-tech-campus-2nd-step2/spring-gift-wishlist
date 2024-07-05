package gift.controller;

import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/signup")
    public String signUp() {
        return "user/signup";
    }

    @PostMapping("/api/signup")
    public String SignUp(@RequestParam("user_id") String user_id, @RequestParam("password") String password) {
        userService.signUp(user_id,password);
        return "redirect:/user/signin";
    }

    @GetMapping("/user/signin")
    public String signIn() {
        return "user/signin";
    }

//    @PostMapping("/api/signup")
//    public void signIn(@RequestParam("user_id") String user_id, @RequestParam("password") String password) {
//        userService.signIn(user_id,password);
//    }

}
