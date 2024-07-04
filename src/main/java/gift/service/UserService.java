package gift.service;

import gift.DTO.Token;
import gift.DTO.User;
import gift.domain.UserEntity;
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
    public void createUser(User user){
        userRepository.save(new UserEntity(user));
    }
    /*
     * User의 정보를 email을 기준으로 찾는 로직
     */
    public User loadOneUser(String email){
        UserEntity byEmail = userRepository.findByEmail(email);
        return new User(byEmail);
    }
    /*
     * User의 정보를 갖고 Token을 생성하는 로직
     */
    public Token makeToken(User user){
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
