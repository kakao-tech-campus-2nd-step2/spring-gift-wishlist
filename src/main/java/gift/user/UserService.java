package gift.user;

import com.github.dockerjava.api.exception.UnauthorizedException;
import gift.token.JwtProvider;
import gift.token.Token;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public final UserRepository userRepository;
    public final JwtProvider jwtProvider;

    public UserService(
        UserRepository userRepository,
        JwtProvider jwtProvider
    ) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public void addUser(User user) {
        if (userRepository.existUserByEmail(user.email())) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepository.addUser(user);
    }

    public Token login(User user) {
        if (!userRepository.existUserByEmail(user.email())) {
            throw new UnauthorizedException("User does not exist");
        }

        User findUser = userRepository.findUserByEmail(user.email());

        if (!findUser.password().equals(user.password())) {
            throw new UnauthorizedException("Wrong password");
        }

        return jwtProvider.generateToken(user);
    }
}
