package gift.Service;

import gift.Model.User;
import gift.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
        userRepository.createUserTable();
    }

    public void signUpUser(User user){
        userRepository.insertUser(user.getEmail(), user.getPassword());
    }
}
