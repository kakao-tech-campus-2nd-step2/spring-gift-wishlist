package gift.service;
import gift.util.JwtUtil;
import gift.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
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
            String token = jwtUtil.generateJWT(user.get());
            return Optional.of(token);
        }
        return Optional.empty();
    }
    public boolean validateToken(String token) {
        return jwtUtil.checkValidateToken(token);
    }

    public Optional<User> getUserByToken(String token) {
        try {
            String email = jwtUtil.getUserEmail(token);
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

}