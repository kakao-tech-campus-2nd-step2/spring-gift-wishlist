package gift.service;

import gift.entity.User;
import gift.exception.*;
import gift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void signUp(String userId, String password) {
        if(userRepository.isExistAccount(userId))
            throw new Exception404("이미 존재하는 계정");
        userRepository.saveUser(new User(userId, password));
    }
}
