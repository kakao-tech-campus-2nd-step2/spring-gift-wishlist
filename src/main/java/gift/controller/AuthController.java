package gift.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gift.exception.InvalidUserException;
import gift.model.User;
import gift.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			throw new InvalidUserException(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		authService.createUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidUserException(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        User registeredUser = authService.searchUser(user.getEmail());
        if (!registeredUser.getPassword().equals(user.getPassword())) {
            throw new InvalidUserException("The email doesn't exist or the password is incorrect.", HttpStatus.FORBIDDEN);
        }
        String token = authService.grantAccessToken(registeredUser);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
