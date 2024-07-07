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

    public ResponseEntity<Long> save(UserDto.Request request) {
        return userRepository.save(request);
    }

    public UserDto login(UserDto.Request inputInfo) {
        UserDto dbUserDto = userRepository.findByUserEmail(inputInfo.getUserEmail());
        if (dbUserDto != null) { return checkPassword(inputInfo, dbUserDto); }
        return null;
    }

    private UserDto checkPassword(UserDto.Request inputInfo, UserDto dbUserDto) {
        if (inputInfo.getUserPassword().equals(dbUserDto.getUserPassword())) { return dbUserDto; }
        return null;
    }


}
