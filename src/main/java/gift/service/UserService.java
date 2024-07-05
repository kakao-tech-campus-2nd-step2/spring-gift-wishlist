package gift.service;

import gift.dto.TokenDTO;
import gift.dto.UserDTO;
import gift.model.User;
import gift.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(UserDTO userDTO) {
        userRepository.insertUser(new User(
                userDTO.email(),
                userDTO.password()
        ));
    }

    public boolean redundantUser(UserDTO user) {
        return userRepository.countUsers(user.email())
                .orElse(0) > 0;
    }

    public TokenDTO makeToken(UserDTO user) {
        String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
        String accessToken = Jwts.builder()
                .setSubject(user.email())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        return new TokenDTO(accessToken);
    }
}
