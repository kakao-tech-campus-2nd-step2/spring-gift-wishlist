package gift.auth;

import gift.errorException.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtToken jwtToken;

    public Token Login(Login login) {
        if (!loginRepository.isExist(login)) {
            throw new BaseHandler(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.");
        }
        return jwtToken.createToken(login);
    }
}
