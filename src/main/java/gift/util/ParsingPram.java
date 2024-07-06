package gift.util;

import gift.auth.JwtToken;
import gift.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ParsingPram {

    private UserRepository userRepository;
    @Autowired
    private JwtToken jwtToken;

    @Autowired
    public ParsingPram(ApplicationContext context) {
        this.userRepository = context.getBean(UserRepository.class);
    }

    public Long getId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        Long temp = userRepository.getId(jwtToken.getEmail(token));
        return temp;
    }
}
