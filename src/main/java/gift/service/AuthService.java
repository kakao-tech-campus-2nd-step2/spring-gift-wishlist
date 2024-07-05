package gift.service;

import gift.dto.UserDTO;
import gift.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean comparePassword(UserDTO userDTO) {
        UserDTO realUser = userRepository.selectUser(userDTO.email());
        return realUser.password().equals(userDTO.password());
    }
}
