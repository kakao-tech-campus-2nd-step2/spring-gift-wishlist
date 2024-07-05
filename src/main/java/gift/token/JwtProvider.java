package gift.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.exception.InternalServerErrorException;
import gift.user.Member;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Token generateToken(Member member) {
        try {
            String userJson = objectMapper.writeValueAsString(member);
            String base64UserJson = Base64.getEncoder().encodeToString(userJson.getBytes());
            return new Token(Jwts.builder()
                .setPayload(base64UserJson)
                .compact());
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException("Failed to generate token");
        }
    }
}
