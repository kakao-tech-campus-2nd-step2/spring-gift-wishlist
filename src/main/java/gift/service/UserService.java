package gift.service;
import gift.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private Map<String, String> tokenStore = new ConcurrentHashMap<>();
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
            String token = UUID.randomUUID().toString();
            User existingUser = user.get();
            existingUser.setToken(token);
            userRepository.updateUserToken(existingUser.getId(), token);
            return Optional.of(token);
        }
        return Optional.empty();
    }
    public boolean validateToken(String token) {
        return userRepository.findByToken(token).isPresent();
    }

    public Optional<User> getUserByToken(String token) {
        return userRepository.findByToken(token);
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