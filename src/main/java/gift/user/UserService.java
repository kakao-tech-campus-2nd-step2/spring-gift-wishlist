package gift.user;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO) {

        userRepository.insertUser(
                new UserDTO(
                        userDTO.getEmail(),
                        userDTO.getPassword(),
                        userDTO.getNickname()));
    }

    public boolean validateUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.email);
        if(user == null) return false;
        return Objects.equals(loginDTO.getPassword(), user.getPassword());
    }

}
