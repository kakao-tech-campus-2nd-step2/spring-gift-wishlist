package gift.service;

import gift.dto.UserDto;
import gift.entity.User;
import gift.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListService {

    private UserRepository userRepository;

    public UserListService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAll() {
        return userRepository.getAll();
    }
}
