package gift.controller;


import gift.dto.TokenDTO;
import gift.dto.UserDTO;
import gift.service.AuthService;
import gift.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    private final String emailErrorMessage = "존재하지 않는 이메일입니다.";
    private final String passwordErrorMessage = "잘못된 비밀번호입니다.";


    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO user) {
        boolean userExists = userService.redundantUser(user);
        if (!userExists) {
            Map<String, String> error = new HashMap<>();
            error.put("message", emailErrorMessage);
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }

        boolean validLogin = authService.comparePassword(user);
        if(validLogin) {
            TokenDTO token = userService.makeToken(user);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        Map<String, String> error = new HashMap<>();
        error.put("message", passwordErrorMessage);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
