package gift.product.validation;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginValidation {
    private final ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();

    public void logIn(String email, String token) {
        tokenMap.put(email, token);
    }

    public void logOut(String email) {
        tokenMap.remove(email);
    }

    public boolean stillLogin(String email) {
        return tokenMap.get(email) != null;
    }
}
