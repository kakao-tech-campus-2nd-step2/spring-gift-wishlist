package gift.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gift.dto.UserDto;
import gift.dto.request.LoginRequest;
import gift.service.UserService;
import gift.util.JwtUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
public class UserController {

    private UserService userService;
    private JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        userService.addUser(userDto);
        String token = jwtUtil.generateToken(userDto);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        UserDto userDto = userService.findByPassword(loginRequest.getPassword());
        String token = jwtUtil.generateToken(userDto);
        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
    }
}
