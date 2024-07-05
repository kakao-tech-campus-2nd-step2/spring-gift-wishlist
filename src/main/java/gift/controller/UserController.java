package gift.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.entity.Token;

import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/signup")
    public String signUp() {
        return "user/signup";
    }

    @PostMapping("/api/signup")

    public String SignUp(@RequestParam("email") String email, @RequestParam("password") String password) {
        userService.signUp(email,password);
        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public String signIn() {
        return "user/signin";
    }

    @PostMapping("/api/signin")
    @ResponseBody
    public String signIn(@RequestParam("email") String email, @RequestParam("password") String password) throws JsonProcessingException {
        Token token = userService.signIn(email,password);
        return objectMapper.writeValueAsString(token);
    }

}
