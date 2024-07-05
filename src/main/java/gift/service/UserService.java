package gift.service;

import gift.DTO.Token;
import gift.DTO.UserDTO;
import gift.domain.User;
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
    /*
     * User의 정보를 저장하는 로직
     */
    public void createUser(UserDTO user){
        userRepository.save(new User(
                user.getEmail(),
                user.getPassword()
        ));
    }
    /*
     * User의 정보를 email을 기준으로 찾는 로직
     */
    public UserDTO loadOneUser(String email){
        User byEmail = userRepository.findByEmail(email);
        return new UserDTO(
                byEmail.getEmail(),
                byEmail.getPassword()
        );
    }
    /*
     * User의 정보를 갖고 Token을 생성하는 로직
     */
    public Token makeToken(UserDTO user){
        Token token = new Token();

        String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
        String accessToken = Jwts.builder()
                .setSubject(user.getEmail().toString())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        token.setToken(accessToken);

        return token;
    }
}
