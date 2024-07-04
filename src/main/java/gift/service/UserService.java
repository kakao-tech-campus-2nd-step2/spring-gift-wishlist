package gift.service;

import gift.Util.JWTUtil;
import gift.entity.Token;
import gift.entity.User;
import gift.exception.*;
import gift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void signUp(String email, String password) {
        if(userRepository.isExistAccount(email))
            throw new Exception400("이미 존재하는 계정");
        userRepository.saveUser(new User(email, password));
    }

    public Token signIn(String email, String password){
        User user = userRepository.findUserbyID(email);
        if(!userRepository.isExistAccount(email))
            throw new Exception404("존재하지 않는 계정");
        if (!passwordEncoder.matches(password, user.getUserPw()))
            throw new Exception400("비밀번호가 일치하지 않습니다.");

        return new Token(JWTUtil.generateToken(user));
    }
}
