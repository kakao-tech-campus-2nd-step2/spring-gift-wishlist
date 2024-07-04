package gift.service;

import gift.dto.UserRequestDTO;
import gift.model.User;
import gift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(UserRequestDTO userRequest) {
        User user = new User(userRequest.getEmail(), userRequest.getPassword());
        userRepository.save(user);
    }

    public String authenticate(UserRequestDTO userRequest) {
        Optional<User> userOpt = userRepository.findByEmail(userRequest.getEmail());
        if (userOpt.isEmpty() || !userOpt.get().checkPassword(userRequest.getPassword())) {
            return null;
        }
        return "access-token";
    }
}
