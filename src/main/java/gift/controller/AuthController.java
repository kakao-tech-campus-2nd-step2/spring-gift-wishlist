package gift.controller;

import gift.domain.User;
import gift.dto.AuthenticationRequest;
import gift.dto.AuthenticationResponse;
import gift.service.UserService;
import gift.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class AuthRestController {
    private final UserService userService;
    private JwtUtil jwtUtil;
    @Autowired
    public AuthRestController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        userService.save(user);
        String jwt = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = userService.findByEmail(authenticationRequest.getEmail());
        if(user == null || !user.getPassword().equals(authenticationRequest.getPassword())) {
            return ResponseEntity.status(401).body("이메일과 비밀번호가 적절하지 않습니다.");
        }
        String jwt = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
