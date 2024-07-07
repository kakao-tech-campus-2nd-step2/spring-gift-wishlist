package gift.service;

import gift.entity.User;
import gift.authorization.JwtUtil;
import gift.exceptionhandler.DuplicateValueException;
import gift.repository.JdbcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final JdbcUserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginService(JdbcUserRepository repository , JwtUtil jwtUtil) {
        this.userRepository = repository;
        this.jwtUtil = jwtUtil;
    }

    public boolean saveUser(User user) {
        if(userRepository.save(user)
                .isEmpty()){ //저장하지 못했다면
            return false;
        }
        return true;
    }

    public boolean login(User user) {
        String token = jwtUtil.generateToken(user);
        if(userRepository.isExistUser(user)
                .isEmpty()) {
            throw new DuplicateValueException("회원가입 실패.");
        }
        return true;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
