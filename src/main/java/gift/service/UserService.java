package gift.service;

import gift.dto.UserRequestDto;
import gift.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserRequestDto userRequestDto){
        userRepository.save(userRequestDto.toEntity());
    }

}
