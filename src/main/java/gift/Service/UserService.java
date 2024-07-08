package gift.Service;

import gift.Exception.ForbiddenException;
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


    public String loginUser(User user) throws ForbiddenException {
        String temp = userRepository.login(user.getEmail(), user.getPassword());
        if (!(temp.equals(user.getPassword())))
            throw new ForbiddenException("잘못된 로그인입니다");

        return "login succeed";
    }

}
