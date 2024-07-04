package gift.user;

import com.github.dockerjava.api.exception.UnauthorizedException;
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

    public String login(User user){
        if (!userRepository.existUserByEmail(user.email())) {
            throw new UnauthorizedException("User does not exist");
        }
        User findUser = userRepository.findUserByEmail(user.email());
        if (!findUser.password().equals(user.password())){
            throw new UnauthorizedException("Wrong password");
        }

        // TODO Token 생성 로직 추가 및 TOKEN 생성 결과값 리턴
        return "token";
    }
}
