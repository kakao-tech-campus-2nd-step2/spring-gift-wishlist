package gift.controller;


import gift.common.ErrorMessage;
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
@RequestMapping("/members/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO userDTO) {
        authService.redundantUser("login", userDTO);
        authService.comparePassword(userDTO);

        return new ResponseEntity<>(authService.makeToken(userDTO), HttpStatus.OK);
    }

}
