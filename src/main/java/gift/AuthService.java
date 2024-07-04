package gift;


import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String authenticate(String email, String password) {

        if ("admin@email.com".equals(email) && "password".equals(password)) {

            return "";
        }
        throw new AuthenticationException("error");

    }
}