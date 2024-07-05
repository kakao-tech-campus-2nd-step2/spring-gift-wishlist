package gift.service;

import gift.domain.User;
import gift.dto.requestDTO.UserRequestDTO;
import gift.repository.UserRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(UserRequestDTO userRequestDTO){
        User user = UserRequestDTO.toEntity(userRequestDTO);
        userRepository.insertUser(user);
    }

    public User findById(Long id){
        return userRepository.selectUserById(id)
            .orElseThrow(()-> new NoSuchElementException("회원이 없습니다."));
    }

    public User findByEmail(UserRequestDTO userRequestDTO){
        return userRepository.selectUserByEmail(userRequestDTO.email())
            .orElseThrow(()-> new NoSuchElementException("회원이 없습니다."));
    }
}
