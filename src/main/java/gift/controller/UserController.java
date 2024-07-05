package gift.controller;

import gift.dto.UserLogin;
import gift.dto.UserSignUp;
import gift.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @ResponseBody
    @PostMapping("/api/member/signUp")
    public UserSignUp.Response signUp(@RequestBody UserSignUp.Request request){
        String accessToken = userService.signUp(request);

        return UserSignUp.Response.builder()
            .accessToken(accessToken)
            .build();
    }

    @ResponseBody
    @PostMapping("/api/member/login")
    public UserLogin.Response login(@RequestBody UserLogin.Request request){
        String accessToken = userService.login(request);

        return UserLogin.Response.builder()
            .accessToken(accessToken)
            .build();
    }
}
