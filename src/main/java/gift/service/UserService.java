package gift.service;
import gift.model.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {


    private UserRepository userRepository;
    private final static String secretKey = "mySecretKey";
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Optional<String> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            String token = generateJWT(user.get());
            return Optional.of(token);
        }
        return Optional.empty();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<User> getUserByToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    public void addGiftToUser(Long userId, Long giftId) {
        userRepository.addGiftToUser(userId, giftId);
    }

    public void removeGiftFromUser(Long userId, Long giftId) {
        userRepository.removeGiftFromUser(userId, giftId);
    }

    public List<Gift> getGiftsForUser(Long userId) {
        return userRepository.getGiftsForUser(userId);
    }

    private String generateJWT(User user) {
        long expirationTime = System.currentTimeMillis() + 3600000;
        Date expirationDate = new Date(expirationTime);

        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return token;
    }

}