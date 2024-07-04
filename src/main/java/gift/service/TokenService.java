package gift.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final Map<String, String> tokenStore = new HashMap<>();

    public String generateToken(String email) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, email);
        return token;
    }

    public String getEmailFromToken(String token) {
        return tokenStore.get(token);
    }

    public boolean validateToken(String token, String email) {
        String storedEmail = tokenStore.get(token);
        return storedEmail != null && storedEmail.equals(email);
    }

    public void invalidateToken(String token) {
        tokenStore.remove(token);
    }
}
