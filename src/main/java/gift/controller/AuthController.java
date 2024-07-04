package gift.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E";
	
	private String grantAccessToken(User user) {
		return Jwts.builder()
		    .setSubject(user.getId().toString())
		    .claim("email", user.getEmail())
		    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
		    .compact();
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			throw new InvalidUserException(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		User createdUser = authService.createUser(user);
		Map<String, String> response = new HashMap<>();
		response.put("token", grantAccessToken(createdUser));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody User user, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			throw new InvalidUserException(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
		User registeredUser = authService.getUser(user.getEmail());
		if(registeredUser==null || !registeredUser.getPassword().equals(user.getPassword())) {
			throw new InvalidUserException("The email doesn't exist or the password is incorrect.", HttpStatus.FORBIDDEN);
		}
		Map<String, String> response = new HashMap<>();
		response.put("token", grantAccessToken(registeredUser));
		return ResponseEntity.ok(response);
	}
}
