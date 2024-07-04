package gift.service;

import gift.DTO.SignupRequest;
import gift.DTO.User;
import gift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(SignupRequest signupRequest) {
        User user = new User(signupRequest.getEmail(), signupRequest.getPassword());
        userRepository.addUser(user);
    }
}
