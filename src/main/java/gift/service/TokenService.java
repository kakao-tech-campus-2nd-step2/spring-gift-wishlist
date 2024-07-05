package gift.service;

import gift.domain.AuthToken;
import gift.exception.UnAuthorizationException;
import gift.repository.token.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String tokenSave(String token, String email){
        return tokenRepository.tokenSave(token, email);
    }

    public AuthToken findToken(String token){
        AuthToken authToken = tokenRepository.findTokenByToken(token)
                .orElseThrow(UnAuthorizationException::new);

        return authToken;
    }
}
