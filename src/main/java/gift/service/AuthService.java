package gift.service;

import gift.dto.UserDto;
import gift.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String save(UserDto.Request request) {
        if (userRepository.save(request)) {
            return generateToken(request.getUserEmail(), request.getUserPassword());
        }
        return "";
    }

    public String generateToken(String userEmail, String userPassword) {
        String credentials = userEmail + ":" + userPassword;
        return "Basic" + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    public UserDto login(UserDto.Request inputInfo) {
        UserDto dbUserDto = userRepository.findByUserEmail(inputInfo.getUserEmail());
        if (dbUserDto != null) {
            return checkPassword(inputInfo, dbUserDto);
        }
        return null;
    }

    private UserDto checkPassword(UserDto.Request inputInfo, UserDto dbUserDto) {
        if (inputInfo.getUserPassword().equals(dbUserDto.getUserPassword())) {
            return dbUserDto;
        }
        return null;
    }
}
