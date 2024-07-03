package gift.controller;

import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/signin")
    public void SignIn(String user_id, String password) {
        userService.signin(user_id,password);
    }


}
