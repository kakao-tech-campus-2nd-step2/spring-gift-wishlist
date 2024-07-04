package gift.controller;


import static gift.util.ResponseEntityUtil.responseError;

import gift.dto.JwtDTO;
import gift.dto.UserDTO;
import gift.service.UserService;
import gift.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Validated
@RequestMapping("/members")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserDTO userDTO) {
        String token;
        try {
            userService.signUp(userDTO);
            token = JwtUtil.generateToken(userDTO.email());
        } catch (RuntimeException e) {
            return responseError(e);
        }
        return new ResponseEntity<>(new JwtDTO(token), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDTO userDTO) {
        String token;
        try {
            userService.login(userDTO);
            token = JwtUtil.generateToken(userDTO.email());
        } catch (RuntimeException e) {
            return responseError(e);
        }
        return new ResponseEntity<>(new JwtDTO(token), HttpStatus.OK);
    }
}
