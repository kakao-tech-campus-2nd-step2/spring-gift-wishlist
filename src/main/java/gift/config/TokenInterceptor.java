package gift.config;

import gift.exception.UnauthorizedException;
import gift.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    public TokenInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("토큰이 없거나, 헤더 형식에 맞지 않습니다.");
        }

        String token = authorizationHeader.substring(7);
        String email = tokenService.extractEmail(token);

        if (email == null || !tokenService.validateToken(token, email)) {
            throw new UnauthorizedException("토큰이 유효하지 않습니다.");
        }

        request.setAttribute("email", email);
        return true;
    }
}
