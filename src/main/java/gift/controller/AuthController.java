package gift.controller;

import gift.dao.UserDAO;
import gift.dto.AuthRequest;
import gift.dto.AuthResponse;
import gift.entity.User;
import gift.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final TokenService tokenService;
    private final UserDAO userDao;

    @Autowired
    public AuthController(TokenService tokenService, UserDAO userDao) {
        this.tokenService = tokenService;
        this.userDao = userDao;
    }

    @PostMapping("/login/token")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        User user = userDao.findByEmail(authRequest.getEmail());

        if (user == null || !user.getPassword().equals(authRequest.getPassword())) {
            ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
            problemDetail.setDetail("잘못된 인증입니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
        }

        String token = tokenService.generateToken(user.getEmail());
        AuthResponse response = new AuthResponse(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/protected") //Interceptor 작동 확인을 위한 메서드 입니다!
    public ResponseEntity<?> getProtectedPage(HttpServletRequest request) {
        String token = (String) request.getAttribute("token");
        String email = tokenService.getEmailFromToken(token);
        return ResponseEntity.ok("인증되었습니다, " + email);
    }
}
