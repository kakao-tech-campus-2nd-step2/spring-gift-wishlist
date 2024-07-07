package gift.service;

import gift.authorization.JwtUtil;
import gift.entity.User;
import gift.repository.JdbcSignUpRepository;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SignUpService {
    private final JdbcSignUpRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public SignUpService(JdbcSignUpRepository repository , JwtUtil jwtUtil) {
        this.userRepository = repository;
        this.jwtUtil = jwtUtil;
    }
    
    // 에러 터지면 -> global에서 처리해줌
    public ResponseEntity<Map<String, String>> signUp(User user) {
        userRepository.save(user);
        String token = jwtUtil.generateToken(user);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token)
                .body(responseBody);
    }

    @Description("임시 확인용 service")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
