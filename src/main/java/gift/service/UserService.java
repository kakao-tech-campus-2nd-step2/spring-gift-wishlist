package gift.service;

import gift.dto.UserLogin;
import gift.dto.UserSignUp.Request;
import gift.exception.UserErrorCode;
import gift.exception.UserException;
import gift.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public String signUp(Request request) {
        // todo 이미 존재하는 이메일인지 검증 추가

        String _email = "_".concat(request.getEmail());
        userRepository.save(request, _email);
        return _email;
    }

    public String login(UserLogin.Request request) {
        String accessToken = userRepository.findByEmailAndPassword(request)
            .orElseThrow(() -> new UserException.Forbidden(UserErrorCode.WRONG_LOGIN));
        return accessToken;
    }
}
