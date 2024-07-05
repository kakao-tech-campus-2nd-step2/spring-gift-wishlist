package gift.service;

import gift.PasswordEncoder;
import gift.model.User;
import gift.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    private String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(PasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && PasswordEncoder.matches(password, user.getPassword())) {
            return Jwts.builder()
                .setSubject(user.getId().toString())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        }
        return null;
    }
}