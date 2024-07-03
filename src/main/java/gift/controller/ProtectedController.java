package gift.controller;

import gift.model.User;
import gift.model.UserRepository;
import gift.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/protected")
public class ProtectedController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getProtectedResource(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "").trim();
        if (userService.validateToken(token)) {
            return ResponseEntity.ok("Protected resource accessed!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}