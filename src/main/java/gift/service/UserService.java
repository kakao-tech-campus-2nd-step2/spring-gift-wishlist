package gift.service;

import gift.dto.UserDTO;
import gift.model.User;
import gift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Value("${jwt.secretKey}")
    private String secretKey;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(UserDTO userDTO) {
        userRepository.insertUser(new User(
                userDTO.email(),
                userDTO.password(),
                "user"
        ));
    }

}
