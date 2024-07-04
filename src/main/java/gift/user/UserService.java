package gift.user;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        if (userRepository.existUserByEmail(user.email())) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepository.addUser(user);
    }
}
