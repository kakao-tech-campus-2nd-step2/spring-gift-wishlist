package gift.controller;

import gift.dto.TokenDTO;
import gift.dto.UserDTO;
import gift.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    private final String erroeMessage = "이미 가입한 이메일입니다.";

    @GetMapping
    public String registerPage() {
        return "register";
    }

    @PostMapping
    public ResponseEntity<?> registUser(@Valid @RequestBody UserDTO user) {
        boolean userExists = userService.redundantUser(user);

        if (userExists) {
            Map<String, String> error = new HashMap<>();
            error.put("message", erroeMessage);
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

        userService.createUser(user);
        TokenDTO token = userService.makeToken(user);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
