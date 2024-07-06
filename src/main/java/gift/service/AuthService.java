package gift.service;

import gift.dto.UserDto;
import gift.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Long> save(UserDto userDto) {
        return userRepository.save(userDto);
    }
}
