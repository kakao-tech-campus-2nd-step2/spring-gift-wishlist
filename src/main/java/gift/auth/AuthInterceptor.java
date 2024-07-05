package gift.auth;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider tokenProvider;

    public AuthInterceptor(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        String token = tokenProvider.extractJwtTokenFromHeader(request);

        if(ObjectUtils.isEmpty(token)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        if(tokenProvider.isExpired(token)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        Claims claims = tokenProvider.parseToken(token);
        request.setAttribute("email", claims.getSubject());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
    }
}
