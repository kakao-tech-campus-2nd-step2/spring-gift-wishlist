package gift.authService;

import gift.exception.AuthException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {

    private JwtToken jwtToken = new JwtToken();

    /**
     * Authorization 헤더를 검증합니다.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return true if the execution chain should proceed with the next interceptor or the handler itself.
     * @throws Exception in case of errors
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                Claims claims = jwtToken.validateToken(token);
                request.setAttribute("claims", claims);
                return true;
            } catch (Exception e) {
                throw new AuthException("잘못된 토큰 입니다.", HttpStatus.UNAUTHORIZED);
            }
        } else {
            throw new AuthException("토큰이 존재하지 않습니다.", HttpStatus.FORBIDDEN);
        }
    }
}
