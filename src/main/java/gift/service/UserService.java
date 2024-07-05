package gift.service;

import gift.Util.JWTUtil;
import gift.dto.RequestDTO;
import gift.entity.Token;
import gift.entity.User;
import gift.exception.*;
import gift.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void signUp(RequestDTO.SignUpDTO dto) {
        if(userRepository.isExistAccount(dto.getEmail()))
            throw new Exception400("이미 존재하는 계정");
        userRepository.saveUser(new User(dto.getEmail(), dto.getPassword()));
    }

    public Token signIn(RequestDTO.LoginDTO loginDTO){

        if(!userRepository.isExistAccount(loginDTO.getEmail()))
            throw new Exception404("존재하지 않는 계정");
        User user = userRepository.findUserbyID(loginDTO.getEmail());
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new Exception400("비밀번호가 일치하지 않습니다.");

        return new Token(JWTUtil.generateToken(user));
    }
}
